package com.tifone.demo.view.event;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifone.demo.common.tab.MyTabActivity;
import com.tifone.demo.view.R;

public class EventDispatchDemoFragment extends Fragment {
    public static Fragment create(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(MyTabActivity.KEY_FRAGMENT_TITLE, title);
        Fragment fragment = new EventDispatchDemoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_dispatch_demo_layout, container, false);
        view.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(),
                        EventDispatchDemoActivity.class));
            }
        });
        return view;
    }

}
