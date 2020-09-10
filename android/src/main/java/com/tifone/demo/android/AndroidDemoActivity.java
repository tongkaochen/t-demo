package com.tifone.demo.android;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tifone.demo.android.aidl.BookFragment;
import com.tifone.demo.common.DemoBaseActivity;

/**
 * Create by Tifone on 2020/9/10.
 */
public class AndroidDemoActivity extends DemoBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.android_demo_layout);

        ViewGroup root = findViewById(R.id.android_demo_layout_root);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction t = manager.beginTransaction();

        Fragment  fragment = manager.findFragmentById(R.id.android_demo_layout_root);

        if (null == fragment) {
            fragment = new BookFragment();
        }

        t.add(R.id.android_demo_layout_root, fragment);

        t.commit();

    }

    @Override
    protected String getActionBarTitle() {
        return "Android";
    }
}
