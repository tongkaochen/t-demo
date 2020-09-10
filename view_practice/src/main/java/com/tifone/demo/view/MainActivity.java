package com.tifone.demo.view;

import androidx.fragment.app.Fragment;
import android.util.Log;

import com.tifone.demo.common.tab.MyTabActivity;
import com.tifone.demo.view.custom.CustomViewFragment;
import com.tifone.demo.view.event.EventDispatchDemoFragment;

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

    @Override
    protected String getActionBarTitle() {
        return "View练习";
    }

    private void logd(String msg) {
        Log.d("tifone", this.getLocalClassName() + ": " + msg);
    }
}
