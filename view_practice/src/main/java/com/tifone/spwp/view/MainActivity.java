package com.tifone.spwp.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.tifone.spwp.view.event.EventDispatchDemoFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tifone.spwp.view.BaseFragment.KEY_FRAGMENT_TITLE;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {



    private ViewPager mViewPager;
    private TabHost mTabHost;
    private ViewPagerAdapter mAdapter;
    private List<Fragment> mFragments;
    private List<TabHost.TabSpec> mTabSpecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);
        mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup();
        mTabHost.setOnTabChangedListener(this);
        initViewPager();
        initTabHost();
    }
    private void initTabHost() {
        mTabSpecs = new ArrayList<>();
        int index = 0;
        for (Fragment fragment : mFragments) {
            Bundle bundle = fragment.getArguments();
            String title;
            if (bundle != null) {
                title = bundle.getString(KEY_FRAGMENT_TITLE);
            } else {
                title = fragment.getClass().getSimpleName();
            }
            TabHost.TabSpec spec = createSpec(index, title);
            mTabSpecs.add(spec);
            mTabHost.addTab(spec);
            index++;
        }
        mTabHost.setCurrentTab(0);
    }
    private TabHost.TabSpec createSpec(int tag, String title) {
        TabHost.TabSpec spec = mTabHost.newTabSpec(String.valueOf(tag));
        spec.setContent(R.id.tab_spec_content);
        spec.setIndicator(title, getDrawable(R.color.colorAccent));
        return spec;
    }
    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(EventDispatchDemoFragment.create("Event demo"));
    }
    private void createViewPagerAdapter() {
        initFragments();
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mAdapter.setFragments(mFragments);
    }
    private void initViewPager() {
        createViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(mOnPagerChangeListener);
    }
    private final ViewPager.OnPageChangeListener mOnPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            mTabHost.setCurrentTabByTag(String.valueOf(position));
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public void onTabChanged(String tabId) {
        logd("tabId = " + tabId);
        int currentId = Integer.valueOf(tabId);
        mViewPager.setCurrentItem(currentId, true);
    }
    private void logd(String msg) {
        Log.d("tifone", this.getLocalClassName() + ": " + msg);
    }
}
