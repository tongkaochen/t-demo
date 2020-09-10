package com.tifone.demo.osource;

import androidx.fragment.app.Fragment;

import com.tifone.demo.common.tab.MyTabActivity;
import com.tifone.demo.osource.okhttp.OkHttpDemoFragment;
import com.tifone.demo.osource.rxjava.RxJavaDemoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyTabActivity {

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(OkHttpDemoFragment.create("OkHttp demo"));
        fragments.add(RxJavaDemoFragment.create("RxJava demo"));
        return fragments;
    }

    @Override
    protected String getActionBarTitle() {
        return "开源框架";
    }

    @Override
    protected int defaultTab() {
        return 1;
    }
}
