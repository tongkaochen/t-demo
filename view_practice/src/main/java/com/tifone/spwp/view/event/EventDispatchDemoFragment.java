package com.tifone.spwp.view.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.tifone.spwp.common.tab.MyTabActivity;
import com.tifone.spwp.view.R;

public class EventDispatchDemoFragment extends Fragment {
    private Toast mToast;
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
        showToast();
        testWindowView();
    }
    private void testWindowView() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Button button = new Button(getContext());
        button.setText("AAA");
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.height = 100;
        params.width = 100;
        params.gravity = Gravity.CENTER;

        wm.addView(button, params);
    }

    private void showToast() {
        Log.d("tifone", "showToast");
        new Handler().postDelayed(runnable,500);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mToast != null) {
                //mToast.cancel();
            }
            mToast = Toast.makeText(getContext(), "This is a test", Toast.LENGTH_SHORT);
            mToast.show();
            showToast();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_dispatch_demo_layout, container, false);
        view.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), EventDispatchDemoActivity.class));
            }
        });
        return view;
    }

}
