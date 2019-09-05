package com.example.captonesecondstage.ui.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInFragment extends Fragment {
    @BindView(R.id.user_name_et)@Nullable() EditText mUserNameEt;
    @BindView(R.id.password_ed)@Nullable()EditText mPasswordEd;
    @BindView(R.id.remember_cb)@Nullable() CheckBox mRemmeberCb;
    @BindView(R.id.forget_password_btn)@Nullable() Button mForgetPasswordBtn;
    @BindView(R.id.login_btn)@Nullable() Button mLogin;
    @BindView(R.id.create_account_tv)@Nullable() TextView mCreateAccountTv;
    @BindView(R.id.gmail_floatBtn)@Nullable() FloatingActionButton mGmailFloatBtn;
    @BindView(R.id.face_floatBtn)@Nullable() FloatingActionButton mFaceBtn;
    @BindView(R.id.progress_circular)@Nullable()
    ProgressBar mProgressCircular;
    private  static final String MY_PREFS_NAME="EMAILPASSWORD";
    private  static final String EMAIL="EMAIL";
    private static  final String PASS="PASS";
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_log_in, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);
        mAuth=FirebaseAuth.getInstance();
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String email = prefs.getString(EMAIL, "");//""-Empty String is the default value.
        String pass = prefs.getString(PASS, ""); //""-Empty String is the default value.
        if(!email.isEmpty()){
            mRemmeberCb.setChecked(true);
        }
        mPasswordEd.setText(pass);
        mUserNameEt.setText(email);
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
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressCircular.setVisibility(View.VISIBLE);
                checkValidData();
              // startActivity(new Intent(getActivity(), HomePageActivity.class));
            }
        });
        mForgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showForgetPasswordFragments();
            }
        });
    }
    private void checkValidData(){
        String email=mUserNameEt.getText().toString(),
                password=mPasswordEd.getText().toString();
        boolean correct=true;
        if(email==null||email.isEmpty()){
            mUserNameEt.setError("Input Your Email Please!!");
            mProgressCircular.setVisibility(View.GONE);
            correct=false;
        }else{
            mUserNameEt.setError(null);
        }if(password==null||password.isEmpty()){
            mPasswordEd.setError("Input Your Password Please!!");
            mProgressCircular.setVisibility(View.GONE);
            correct=false;
        }else{
            mPasswordEd.setError(null);
        }
        if(correct) {
            if (((MainActivity) getActivity()).checkConnection()) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (mRemmeberCb.isChecked()) {
                                editSharedPreference(email, password);
                            } else {
                                editSharedPreference("", "");
                            }
                            ((MainActivity) getActivity()).goToTheHomePage();
                            mProgressCircular.setVisibility(View.GONE);
                        } else {
                            ((MainActivity) getActivity()).throwException(task);
                            mProgressCircular.setVisibility(View.GONE);
                        }
                    }
                });
            }
            else{
                ((MainActivity) getActivity()).showNoInternetConnectionFragment();
            }
        }

    }
    private  void editSharedPreference(String email,String pass){
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(EMAIL, email);
        editor.putString(PASS, pass);
        editor.apply();
    }
}
