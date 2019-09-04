package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.HomePageActivity;
import com.example.captonesecondstage.ui.Activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment {
    @BindView(R.id.user_name_et)EditText mUserNameEt;
    @BindView(R.id.user_email_et)EditText mUserEmailEt;
    @BindView(R.id.password_ed)EditText mPasswordEd;
    @BindView(R.id.cfPassword_ed) EditText mcfPasswordEt;
    @BindView(R.id.signUp_btn) Button mSignUpBtn;
    @BindView(R.id.gmail_floatBtn) FloatingActionButton mGmailFloatBtn;
    @BindView(R.id.face_floatBtn)FloatingActionButton mFaceFloatBtn;
    @BindView(R.id.create_account_tv) TextView mCreateAccountTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        //initialize Events
        initializeEvent();

        return root;
    }

    private void initializeEvent() {
        mCreateAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showLoginFragment();
            }
        });
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getActivity(), HomePageActivity.class));
            }
        });

    }


}
