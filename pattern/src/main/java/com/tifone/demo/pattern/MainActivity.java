package com.tifone.demo.pattern;

import android.os.Bundle;

import com.tifone.demo.common.DemoBaseActivity;

public class MainActivity extends DemoBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected String getActionBarTitle() {
        return "设计模式";
    }
}
