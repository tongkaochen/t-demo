package com.tifone.demo.common.tab;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private HashMap<String, Fragment> mFragmentMap;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
    }
    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int index) {
        if (index > mFragments.size()) {
            return null;
        }
        return mFragments.get(index);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
