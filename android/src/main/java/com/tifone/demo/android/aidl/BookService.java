package com.tifone.demo.android.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Create by Tifone on 2020/9/10.
 */
public class BookService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new RemoteBookService();
    }
}
