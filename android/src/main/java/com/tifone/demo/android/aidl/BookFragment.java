package com.tifone.demo.android.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tifone.demo.android.R;

/**
 * Create by Tifone on 2020/9/10.
 */
public class BookFragment extends Fragment {

    private IRemoteBookService mService;
    private TextView mText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aidl_layout, container, false);
        mText = view.findViewById(R.id.result_tv);
        view.findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mService) {
                    try {
                        mText.setText(mService.getBook("tifone").toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        bindService();
        return view;
    }

    @Override
    public void onDestroyView() {
        unbindService();
        super.onDestroyView();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IRemoteBookService.Stub.asInterface(service);
            try {
                mService.addBook(new Book("abc", "tifone", 100));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mText.setText("Service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mText.setText("Service disconnected");
        }
    };

    private void bindService() {
        Intent intent = new Intent(getActivity(), BookService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        getActivity().unbindService(mConnection);
    }
}
