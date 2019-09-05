package com.example.captonesecondstage.ui.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NoInternetConnectionFragment extends Fragment {

    @BindView(R.id.btn_is_connect)@Nullable()
    Button mBtnIsConnect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_no_internet_connection, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);
        mBtnIsConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((MainActivity)getActivity()).checkConnection()){
                    ((MainActivity)getActivity()).showLoginFragment();
                }
            }
        });
        return root;
    }


}
