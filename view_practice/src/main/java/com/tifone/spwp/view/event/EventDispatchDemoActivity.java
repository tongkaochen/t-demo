package com.tifone.spwp.view.event;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

import com.tifone.spwp.view.R;

public class EventDispatchDemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_dispatch_layout);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        logd("dispatchTouchEvent:" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        logd("onTouchEvent:" + event.getAction());
        return super.onTouchEvent(event);
    }

    private void logd(String msg) {
        Log.d("tifone", getClass().getSimpleName() + ": " + msg);
    }
}
