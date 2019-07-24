package com.tifone.spwp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<DemoBean> mDataset;
    private DemoAdapter mAdapter;
    private int mFocusPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.demo_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFocusPosition = getResources().getInteger(R.integer.current_demo);
        // read string array for resource
        readDemoFromArray();
        // build recycler view adapter
        createAndSetAdapter();
        // set click listener
    }

    private void readDemoFromArray() {
        mDataset = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.demo_title_entries);
        String[] activities = getResources().getStringArray(R.array.demo_activity_entries);
        for (int i = 0; i < titles.length; i++) {
            mDataset.add(new DemoBean(titles[i], activities[i]));
        }
    }

    private void createAndSetAdapter() {
        mAdapter = new DemoAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    static class DemoBean {
        String title;
        String activity;
        DemoBean(String title, String activity) {
            this.title = title;
            this.activity = activity;
        }
    }

    class DemoAdapter extends RecyclerView.Adapter<DemoViewHolder> {

        @NonNull
        @Override
        public DemoViewHolder onCreateViewHolder(
                @NonNull ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.demo_item, viewGroup, false);
            return new DemoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(
                @NonNull DemoViewHolder demoViewHolder, int position) {
            String title = mDataset.get(position).title;
            final String activity = mDataset.get(position).activity;
            demoViewHolder.btn.setText(position + ": " + title);
            demoViewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    try {
                        intent.setClass(MainActivity.this, Class.forName(activity));
                        MainActivity.this.startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (mFocusPosition == position) {
                demoViewHolder.btn.performClick();
            }
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    class DemoViewHolder extends RecyclerView.ViewHolder {
        Button btn;
        DemoViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }

}
