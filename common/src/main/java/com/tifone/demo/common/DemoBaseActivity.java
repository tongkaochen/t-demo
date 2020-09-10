package com.tifone.demo.common;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Create by Tifone on 2020/9/3.
 */
public abstract class DemoBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null != getSupportActionBar()) {
            getSupportActionBar().setTitle(getActionBarTitle());
        }
    }

    protected abstract String getActionBarTitle();
}
