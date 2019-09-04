package com.example.captonesecondstage.ui.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.captonesecondstage.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContinueSignUp extends Fragment {
    @BindView(R.id.profile_image) CircleImageView mProfileImage;
    @BindView(R.id.user_name_tv) TextView mUserNameTv;
    @BindView(R.id.user_phone_et) EditText mUserPhoneEt;
    @BindView(R.id.address_et) EditText mAdressEt;
    @BindView(R.id.description_et) EditText mDescriptionEt;
    @BindView(R.id.user_type_spinner) Spinner mUserTypeSpinner;
    @BindView(R.id.autoCompleteTv_courses) MultiAutoCompleteTextView  mCoursesAutoComplete;
    @BindView(R.id.signUp_btn) Button mSignUpBtn;
    @BindView(R.id.image_upload) ImageView mImageUpload;

    private static   final String[] COURSES = new String[] {
            "Java", "C++", "Python", "Android ", "JavaEE","HTML","CSS","JavaScript","PHP","Laravel"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_continue_sign_up, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        //initialize Events
        initializeEvent();
        //setAdapters
        setAdapter();
        return root;
    }

    private void initializeEvent() {
        mImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private  void setAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, COURSES);
        mCoursesAutoComplete.setAdapter(adapter);
        mCoursesAutoComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());




    }


}
