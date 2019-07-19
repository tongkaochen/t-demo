package com.tifone.spwp.view.event;

import android.content.Context;
import android.graphics.LinearGradient;
import android.os.ParcelFileDescriptor;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import com.tifone.spwp.view.R;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MyButton extends Button {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        logd("dispatchTouchEvent:" + ev.getAction());
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                //requestLayout();
                setTextColor(getContext().getColor(R.color.colorAccent));
            }
        });

        try {
            ParcelFileDescriptor[] descriptors = ParcelFileDescriptor.createPipe();
            ParcelFileDescriptor readClient = descriptors[0];
            ParcelFileDescriptor writeClient = descriptors[1];
            ParcelFileDescriptor.AutoCloseOutputStream acos =
                    new ParcelFileDescriptor.AutoCloseOutputStream(writeClient);
            acos.write(new byte[102]);
            ParcelFileDescriptor.AutoCloseInputStream acis =
                    new ParcelFileDescriptor.AutoCloseInputStream(readClient);
            acis.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        logd("onTouchEvent:" + event.getAction());
        return super.onTouchEvent(event);
//        return false;
    }
    private void logd(String msg) {
        Log.d("tifone", this.getClass().getSimpleName() + ": " + msg);
    }

}
