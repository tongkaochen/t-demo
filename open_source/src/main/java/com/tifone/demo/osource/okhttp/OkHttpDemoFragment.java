package com.tifone.demo.osource.okhttp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tifone.demo.common.tab.MyTabActivity;
import com.tifone.demo.osource.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by Tifone on 2019/7/8.
 */
public class OkHttpDemoFragment extends Fragment {

    public static Fragment create(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(MyTabActivity.KEY_FRAGMENT_TITLE, title);
        Fragment fragment = new OkHttpDemoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.okhttp_demo_layout, container, false);
        view.findViewById(R.id.get_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataSync();
            }
        });
        return view;
    }

    private void getDataSync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://www.baidu.com")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        logd("result code = " + response.code());
                        logd("result message = " + response.message());
                        logd("res : = " + response.body().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void logd(String msg) {
        Log.d("tifone", this.getClass().getSimpleName() + " : " + msg);
    }
}
