package com.tifone.demo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static com.tifone.demo.common.tab.log.LogUtil.logd;

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
    private float mCenterLocation;
    private float mCenterIndex = 10f;
    private float mTotalLength;
    private float mItemInterval;
    private float mDx;
    private float mTotalDx;
    private float mScrollX;
    private Paint mTextPaint;

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
        mMainScalePaint.setColor(Color.BLACK);

        // sub scale paint
        mSubScalePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCursorPaint.setStyle(Paint.Style.STROKE);
        mCursorPaint.setStrokeWidth(4f);
        mCursorPaint.setColor(Color.GREEN);

        // text paint
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(16);
        mTextPaint.setColor(Color.BLACK);

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
//        mScrollX -= dx;
        logd("mScrollX = " + mScrollX);
        mCenterLocation += dx;
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // init the view size spec
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = mWidth / 2;
        mItemInterval = (float)mWidth / mVisibleCount;
        mTotalLength = (mMaxScale - mMinScale) * mItemInterval;
        mCenterLocation = getCenterLocation(mCenterIndex);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        mGestureDetector.onTouchEvent(event);
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCenterLocation = getCenterLocation(mCenterIndex);
                logd("mCenterLocation = " + mCenterLocation);
                mScrollX = 0;
                break;
            case MotionEvent.ACTION_UP:
                mCenterLocation = resolveCenterLocation(mCenterLocation + mScrollX);
                logd("mCenterLocation = " + mCenterLocation);
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the center cursor indicator
        float newLocation = resolveCenterLocation(mCenterLocation);
        logd("newLocation = " + newLocation);
        mCenterIndex = getIndex(newLocation);
        drawCursorIndicator(canvas);
        // draw left side
        drawLeftSide(canvas, newLocation);
        // draw right side
        drawRightSide(canvas, newLocation);
    }

    private void drawCursorIndicator(Canvas canvas) {
        // indicator must place in the center of view
        // it may be the bitmap indicator
        canvas.drawLine(mCenterX, 0, mCenterX, mHeight, mCursorPaint);
        canvas.drawLine(0, mHeight / 2.0f, mWidth, mHeight / 2.0f, mCursorPaint);
    }
    private void drawLeftSide(Canvas canvas, float newLocation) {
        // specify the visible count
        // main scale and sub scale
        float leftWidth = mWidth / 2.0f + mScrollX;
        int positionTop = mHeight / 4;
        int positionBottom = positionTop + mHeight / 2;
        for(float offset = 0f; offset <= leftWidth; offset += mItemInterval) {
            float xV = newLocation - offset;
            xV = resolveCenterLocation(xV);
            int index = Math.round(getIndex(xV));
            logd("xV = " + xV + " index = " + index);
            drawScale(canvas, index, leftWidth - offset, positionTop,
                    leftWidth - offset, positionBottom);
        }
    }
    private void drawScale(Canvas canvas, int index, float startX,
                           float startY, float endX, float endY) {
        if ( index % 10 == 0) {
            canvas.drawLine(startX, startY,
                    endX, endY, mMainScalePaint);
        } else {
            // draw stub scale
            canvas.drawLine(startX, startY,
                    endX, endY, mSubScalePaint);
        }
        canvas.drawText("" + index, endX,
                endY, mTextPaint);
    }
    private float getIndex(float location) {
        return location / mItemInterval + mMinScale;
    }
    private float getCenterLocation(float index) {
        return mItemInterval * (index - mMinScale);
    }
    // centerLocation, centerIndex
    // scroll, dx value
    // draw start with centerLocation + dx,
    // draw left(length = width/2 + dx) and right(width/2 - dx)
    // up event
    // the new centerLocation, centerIndex
    private void drawRightSide(Canvas canvas, float newLocation) {
        // specify the visible count
        // main scale and sub scale
        float rightWidth = mWidth / 2.0f - mScrollX;
        int positionTop = mHeight / 4;
        int positionBottom = positionTop + mHeight / 2;
        for(float offset = 0f; offset <= rightWidth; offset += mItemInterval) {
            float xV = newLocation + offset;
            xV = resolveCenterLocation(xV);
            int index = Math.round(getIndex(xV));
            float xP =  mWidth / 2.0f + mScrollX + offset;
            drawScale(canvas, index, rightWidth + offset, positionTop,
                    rightWidth + offset, positionBottom);
        }
    }
    private float resolveCenterLocation(float currentLocation) {
        // between 0 ~ mTotalLength
        if (currentLocation < 0) {
            return mTotalLength + currentLocation;
        } else if (currentLocation > mTotalLength) {
            return currentLocation - mTotalLength;
        }
        return currentLocation;
    }
}
