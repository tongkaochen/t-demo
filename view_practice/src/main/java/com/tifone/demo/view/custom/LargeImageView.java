package com.tifone.demo.view.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.InputStream;

import static com.tifone.demo.common.tab.log.LogUtil.logd;

public class LargeImageView extends View implements GestureEventDetection.GestureListener {
    private LargeImageDecoder mDecoder;
    private GestureEventDetection mDetection;
    private RectF mRectF;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private BitmapFactory.Options mOptions;
    private Matrix mMatrix;
    private Size size;
    private float mTempScale = 1f;

    public LargeImageView(Context context) {
        this(context, null);
    }

    public LargeImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LargeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDetection = new GestureEventDetection(getContext(), this);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        mOptions = new BitmapFactory.Options();
        mOptions.inSampleSize = 10;

        mMatrix = new Matrix();
        mMatrix.setScale(1f, 1f);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus) {
//            mRect = new Rect(0, 0, getWidth(), getHeight());
        }
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        mDetection.start(event);
        return true;
    }

    public void setImageResource(InputStream is) {
        mDecoder = new LargeImageDecoder(is);
        size = mDecoder.getSize();
        mRectF = new RectF(0, 0, mWidth, mHeight);
        mMatrix.mapRect(mRectF);
        mOptions.inSampleSize = getSampleSize((int) ((mRectF.right - mRectF.left) / mWidth));
        recycleBitmap();
        mBitmap = mDecoder.decodeRegion(round(mRectF), mOptions);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null) {
            return;
        }
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        recycleBitmap();
    }
    public static int getSampleSize(int size) {
        if (size <= 1) return 1;
        int sampleSize = 1;
        while (size > 1) {
            size >>= 1;
            sampleSize <<= 1;
        }
        return sampleSize;
    }
    private void updateBitmap() {
        if (mBitmap.getWidth() > mWidth) {
            Matrix matrix = new Matrix();
            matrix.setScale((float)mWidth / mBitmap.getWidth(), (float)mWidth / mBitmap.getWidth());
            Bitmap bitmap = Bitmap.createBitmap(mBitmap, 0,0,mBitmap.getWidth(),
                    mBitmap.getHeight(), matrix, false);
            recycleBitmap();
            mBitmap = bitmap;
        }
    }
    @Override
    public void onScale(float scale) {
        logd("onScale = " + mTempScale);
        mTempScale = mTempScale * scale;
        mMatrix.setScale(mTempScale, mTempScale);
        mMatrix.mapRect(mRectF);
        mOptions.inSampleSize = getSampleSize((int) mTempScale);
        center(mRectF);
        recycleBitmap();
        mBitmap = mDecoder.decodeRegion(round(mRectF), mOptions);
        if (mBitmap == null) {
            return;
        }
        updateBitmap();
        invalidate();
    }

    private void center(RectF rectf) {
        float imageCenterX = size.getWidth() / 2.0f;
        float imageCenterY = size.getHeight() / 2.0f;
        float width = rectf.right - rectf.left;
        float height = rectf.bottom - rectf.top;
        float centerX = rectf.right - width / 2.0f;
        float centerY = rectf.bottom - height / 2.0f;
        Matrix matrix = new Matrix();
        matrix.setTranslate(imageCenterX - centerX, imageCenterY - centerY);
        matrix.mapRect(rectf);
    }

    private Rect round(RectF f) {
        return new Rect(Math.round(f.left),
                Math.round(f.top),
                Math.round(f.right),
                Math.round(f.bottom));
    }

    private void recycleBitmap() {
        if (mBitmap !=null && !mBitmap.isRecycled()) {
            logd("recycleBitmap");
            mBitmap.recycle();
        }
    }

    @Override
    public void onScroll(float dx, float dy) {
        logd("onScroll");
        mRectF.offset(dx, dy);
        recycleBitmap();
        mBitmap = mDecoder.decodeRegion(round(mRectF), mOptions);
        updateBitmap();
        invalidate();
    }

    @Override
    public void onFling(float dx, float dy) {

    }

    @Override
    public void onDoubleTap(float x, float y) {

    }

    @Override
    public void onTap(float x, float y) {

    }

    @Override
    public void onLongPress(float x, float y) {

    }
}
