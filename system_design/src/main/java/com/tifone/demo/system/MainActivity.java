package com.tifone.demo.system;

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
        return "系统设计";
    }


}