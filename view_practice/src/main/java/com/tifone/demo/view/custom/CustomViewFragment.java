package com.tifone.demo.view.custom;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifone.demo.common.tab.MyTabActivity;
import com.tifone.demo.view.R;

public class CustomViewFragment extends Fragment {
    public static Fragment create(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(MyTabActivity.KEY_FRAGMENT_TITLE, title);
        Fragment fragment = new CustomViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_view_demo_layout, container, false);
        return view;
    }
}
