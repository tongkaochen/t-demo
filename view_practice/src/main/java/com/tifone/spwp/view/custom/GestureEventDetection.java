package com.tifone.spwp.view.custom;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class GestureEventDetection implements GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener {

    private final ScaleGestureDetector mScaler;

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        mListener.onScale(detector.getScaleFactor());
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    interface GestureListener {
        void onScale(float scale);
        void onScroll(float dx, float dy);
        void onFling(float dx, float dy);
        void onDoubleTap(float x, float y);
        void onTap(float x, float y);
        void onLongPress(float x, float y);
    }
    private GestureDetector mDetector;
    private GestureListener mListener;

    public GestureEventDetection(Context context, GestureListener listener) {
        mDetector = new GestureDetector(context, this);
        mScaler = new ScaleGestureDetector(context, this);
        mListener = listener;
    }

    public void start(MotionEvent event) {
        if (mListener == null) {
            return;
        }
        mDetector.onTouchEvent(event);
        mScaler.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        mListener.onTap(e.getRawX(), e.getRawY());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        mListener.onScroll(distanceX, distanceY);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        mListener.onLongPress(e.getRawX(), e.getRawY());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }
}
