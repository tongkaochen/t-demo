package com.tifone.demo.view.event;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;

import com.tifone.demo.view.R;

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
