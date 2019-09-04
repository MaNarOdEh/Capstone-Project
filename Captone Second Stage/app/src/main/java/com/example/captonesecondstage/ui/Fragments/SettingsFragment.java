package com.example.captonesecondstage.ui.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.captonesecondstage.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsFragment extends Fragment {

    @BindView(R.id.btn_update_user_name)@Nullable()
    Button mBtnUpdateUserName;
    @BindView(R.id.btn_update_password)@Nullable()
    Button mBtnUpdatePasssword;
    @BindView(R.id.btn_update_email)@Nullable()
    Button mBtnUpdateEmail;
    @BindView(R.id.btn_update_address)@Nullable()
    Button mBtn_update_address;
    @BindView(R.id.btn_update_courses)@Nullable()
    Button mBtnUpdateCourses;
    @BindView(R.id.btn_update_phone_number)@Nullable()
    Button mBtnUpdatePhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        initializeEvent();

        return root;
    }
    private  void   initializeEvent(){
        mBtnUpdateUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnUpdatePasssword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtn_update_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnUpdateCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnUpdatePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}
