package com.example.captonesecondstage.ui.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInFragment extends Fragment {
    @BindView(R.id.user_name_et) EditText mUserNameEt;
    @BindView(R.id.password_ed)EditText mPasswordEd;
    @BindView(R.id.remember_cb) CheckBox mRemmeberCb;
    @BindView(R.id.forget_password_btn) Button mForgetPasswordBtn;
    @BindView(R.id.login_btn) Button mLogin;
    @BindView(R.id.create_account_tv) TextView mCreateAccountTv;
    @BindView(R.id.gmail_floatBtn) FloatingActionButton mGmailFloatBtn;
    @BindView(R.id.face_floatBtn)FloatingActionButton mFaceBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_log_in, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        //initialize Events
        initializeEvent();

        return  root;
    }

    private void initializeEvent() {
        mCreateAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showSignUpFragment();
            }
        });
    }


}
