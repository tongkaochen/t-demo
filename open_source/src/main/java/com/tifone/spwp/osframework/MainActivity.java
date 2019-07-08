package com.tifone.spwp.osframework;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tifone.spwp.common.tab.MyTabActivity;
import com.tifone.spwp.osframework.okhttp.OkHttpDemoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyTabActivity {

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(OkHttpDemoFragment.create("OkHttp demo"));
        return fragments;
    }

}
