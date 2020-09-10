package com.tifone.demo.osource.rxjava;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifone.demo.common.tab.MyTabActivity;

public class RxJavaDemoFragment extends Fragment {

    public static Fragment create(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(MyTabActivity.KEY_FRAGMENT_TITLE, title);
        Fragment fragment = new RxJavaDemoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        RxJavaDemo demo = new RxJavaDemo();
        demo.demo1();
    }
}
