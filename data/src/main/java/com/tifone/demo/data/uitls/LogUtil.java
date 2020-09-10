package com.tifone.demo.data.uitls;

import android.util.Log;

public class LogUtil {
    public static final String TAG = "tifone";
    public static final boolean DEBUG = true;
    public static void logd(String msg) {
        if (DEBUG) Log.d(TAG, msg);
    }
    public static void logd(Object object, String msg) {
        if (DEBUG) Log.d(TAG, object.getClass().getSimpleName() + ": " + msg);
    }
    public static void logd(String key, String msg) {
        if (DEBUG) Log.d(TAG, key + ": " + msg);
    }

    public static void loge(Object object, String msg) {
        Log.e(TAG, object.getClass().getSimpleName() + ": " + msg);
    }
    public static void loge(String msg) {
        Log.e(TAG, msg);
    }
    public static void loge(String key, String msg) {
        Log.e(TAG, key + ": " + msg);
    }

    public static void logw(String msg) {
        Log.w(TAG, msg);
    }
    public static void logw(Object object, String msg) {
        Log.w(TAG, object.getClass().getSimpleName() + ": " + msg);
    }
    public static void logw(String key, String msg) {
        Log.w(TAG, key + ": " + msg);
    }
}
