package com.tifone.spwp.view.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        logd("dispatchTouchEvent:" + ev.getAction());

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        logd("onInterceptTouchEvent:" + ev.getAction());
        switch (ev.getAction()) {
            case  MotionEvent.ACTION_DOWN:
//                return true;
        }
//        return false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        logd("onTouchEvent:" + event.getAction());
        switch (event.getAction()) {
            case  MotionEvent.ACTION_DOWN:
                return true;
        }
        return super.onTouchEvent(event);
    }
    private void logd(String msg) {
        Log.d("tifone", this.getClass().getSimpleName() + ": " + msg);
    }
}
