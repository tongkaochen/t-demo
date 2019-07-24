package com.tifone.spwp.view;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.tifone.spwp.common.tab.MyTabActivity;
import com.tifone.spwp.view.custom.CustomViewFragment;
import com.tifone.spwp.view.event.EventDispatchDemoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyTabActivity {


    @Override
    public List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(EventDispatchDemoFragment.create("TouchEvent demo"));
        fragments.add(CustomViewFragment.create("CustomView demo"));
        return fragments;
    }

    private void logd(String msg) {
        Log.d("tifone", this.getLocalClassName() + ": " + msg);
    }
}
