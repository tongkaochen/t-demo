package com.tifone.spwp.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static com.tifone.spwp.common.tab.log.LogUtil.logd;

public class MyCursorView2 extends View{
    private int mWidth;
    private int mHeight;
    private Paint mCursorPaint;
    private int mCenterX;
    private Paint mSubScalePaint;
    private Paint mMainScalePaint;
    private int mMinScale = 10;
    private int mMaxScale = 50;
    private int mVisibleCount = 25;
    private GestureDetector mGestureDetector;
    // center cursor position
    private int mCenterLocation;
    private int mCenterIndex = 30;
    private int mTotalLength;
    private int mItemInterval;
    private int mDx;
    private int mTotalDx;

    public MyCursorView2(Context context) {
        this(context, null);
    }

    public MyCursorView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCursorView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        // cursor paint, use to draw the center indicator
        mCursorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCursorPaint.setStyle(Paint.Style.STROKE);
        mCursorPaint.setStrokeWidth(5f);
        mCursorPaint.setColor(Color.BLACK);

        // main scale paint
        mMainScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMainScalePaint.setStyle(Paint.Style.STROKE);
        mMainScalePaint.setStrokeWidth(8f);
        mMainScalePaint.setColor(Color.YELLOW);

        // sub scale paint
        mSubScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCursorPaint.setStyle(Paint.Style.STROKE);
        mCursorPaint.setStrokeWidth(4f);
        mCursorPaint.setColor(Color.GREEN);

        mGestureDetector = new GestureDetector(getContext(), mGestureListener);
    }

    private GestureDetector.SimpleOnGestureListener mGestureListener =
            new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                        float distanceX, float distanceY) {
                    scrollViewTo(distanceX);
                    return true;
                }
            };

    private void scrollViewTo(float dx) {
        mCenterLocation -= dx;
        mCenterLocation = resolveCenterLocation(mCenterLocation);
        logd("mCenterLocation = " + mCenterLocation);
        mDx = (int) dx;
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // init the view size spec
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = mWidth / 2;
        mCenterLocation = mCenterX;
        mItemInterval = mWidth / mVisibleCount;
        mTotalLength = (mMaxScale - mMinScale) * mItemInterval;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        mGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the center cursor indicator
        drawCursorIndicator(canvas);
        getDiffX();
        mCenterIndex = getIndex(mCenterLocation);
        // draw left side
        drawLeftSide(canvas);
        // draw right side
        drawRightSide(canvas);
    }

    private void drawCursorIndicator(Canvas canvas) {
        // indicator must place in the center of view
        // it may be the bitmap indicator
        canvas.drawLine(mCenterX, 0, mCenterX, mHeight, mCursorPaint);
    }
    private void drawLeftSide(Canvas canvas) {
        // specify the visible count
        // main scale and sub scale
        int visibleCount = mVisibleCount / 2;
        int positionX = mCenterX - mTotalDx;
        int positionTop = mHeight / 4;
        int positionBottom = positionTop + mHeight / 2;
        int currentLocation = mCenterLocation;
        for (int i = positionX; i > 0; i -= mItemInterval) {
            currentLocation -= mItemInterval;
            if (getIndex(currentLocation) % 10 == 0) {
                canvas.drawLine(i, positionTop, i, positionBottom, mMainScalePaint);
            } else {
                // draw stub scale
                canvas.drawLine(i, positionTop, i, positionBottom, mSubScalePaint);
            }
        }
    }
    private int getIndex(int position) {
        logd("index = " + (int) (((float)position / mTotalLength) * (mMaxScale - mMinScale) + mMinScale));
        return (int) (((float)position / mTotalLength) * (mMaxScale - mMinScale)) + mMinScale;
    }
    private void drawRightSide(Canvas canvas) {
        // specify the visible count
        // main scale and sub scale
        int visibleCount = mVisibleCount / 2;
        int positionX = mCenterX - mTotalDx;
        int positionTop = mHeight / 4;
        int positionBottom = positionTop + mHeight / 2;
        int currentLocation = mCenterLocation;
        for (int i = positionX; i < mWidth; i += mItemInterval) {
            currentLocation += mItemInterval;
            if (getIndex(currentLocation) % 10 == 0) {
                canvas.drawLine(i, positionTop, i, positionBottom, mMainScalePaint);
            } else {
                // draw stub scale
                canvas.drawLine(i, positionTop, i, positionBottom, mSubScalePaint);
            }
        }
    }
    private int getDiffX() {
        mTotalDx += mDx;
        if (Math.abs(mTotalDx) > mItemInterval) {
            mTotalDx = Math.abs(mTotalDx) % mItemInterval;
        }
        return mTotalDx;
    }
    private int resolveCenterLocation(int currentLocation) {
        // between 0 ~ mTotalLength
        if (currentLocation < 0) {
            return mTotalLength + currentLocation;
        } else if (currentLocation > mTotalLength) {
            return currentLocation - mTotalLength;
        }
        return currentLocation;
    }
}
