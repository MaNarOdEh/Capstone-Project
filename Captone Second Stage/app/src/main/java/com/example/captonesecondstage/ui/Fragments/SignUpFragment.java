package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.Validation.ValidationData;
import com.example.captonesecondstage.ui.Activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment {
    @BindView(R.id.user_name_et)@Nullable()EditText mUserNameEt;
    @BindView(R.id.user_email_et)@Nullable()EditText mUserEmailEt;
    @BindView(R.id.password_ed)@Nullable()EditText mPasswordEd;
    @BindView(R.id.cfPassword_ed)@Nullable() EditText mcfPasswordEt;
    @BindView(R.id.signUp_btn)@Nullable() Button mSignUpBtn;
    @BindView(R.id.gmail_floatBtn)@Nullable() FloatingActionButton mGmailFloatBtn;
    @BindView(R.id.face_floatBtn)@Nullable()FloatingActionButton mFaceFloatBtn;
    @BindView(R.id.create_account_tv)@Nullable() TextView mCreateAccountTv;

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
                validateInput();

            }

        });

    }
    private void validateInput() {
        String userName=mUserNameEt.getText().toString(),
                userEmail=mUserEmailEt.getText().toString(),
                password=mPasswordEd.getText().toString(),
                cpassword=mcfPasswordEt.getText().toString();
        boolean correct=true;
        if(!ValidationData.isCorrectName(userName)){
            mUserNameEt.setError("Your name must al least have 3 char at the beginning");
            correct=false;
        }else{
            mUserNameEt.setError(null);
        }if(!ValidationData.isCorrectEmail(userEmail)){
            mUserEmailEt.setError("Input Valid Email Please!");
            correct=false;
        }else{
            mUserNameEt.setError(null);
        }if(!ValidationData.isCorrectPassword(password)){
            mPasswordEd.setError("Your password should at least 6 letters with 2 Capital Letter");
        }else{
            mPasswordEd.setError(null);
            if(!password.equals(cpassword)){
                mcfPasswordEt.setError("Your Password not Equals");
                correct=false;
            }else{
                mcfPasswordEt.setError(null);
            }
        }
        if(correct){
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Teachers").hasChild(userName) ){
                        // run some code..this user name already taken before!!

                    }else if(dataSnapshot.child("Students_Parents").hasChild(userName)){
                        //this user name is already taken before
                    }else{
                        ((MainActivity)getActivity()).showContinueSignUpFragment(userName,userEmail,cpassword);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }


}
