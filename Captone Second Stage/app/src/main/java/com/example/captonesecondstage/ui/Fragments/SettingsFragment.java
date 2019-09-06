package com.example.captonesecondstage.ui.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import com.example.captonesecondstage.DataBase.AddingReadingData;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.Validation.ValidationData;
import com.example.captonesecondstage.ui.Activity.HomePageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsFragment extends Fragment {

    @BindView(R.id.btn_update_description)@Nullable()
    Button mBtnUpdateDescription;
    @BindView(R.id.btn_update_address)@Nullable()
    Button mBtn_update_address;
    @BindView(R.id.btn_update_courses)@Nullable()
    Button mBtnUpdateCourses;
    @BindView(R.id.btn_update_phone_number)@Nullable()
    Button mBtnUpdatePhoneNumber;
    @BindView(R.id.main_layout)@Nullable
    LinearLayout mMainLayout;


    @BindView(R.id.address_linear)@Nullable
    LinearLayout mAddressLinear;
    @BindView(R.id.btn_update_addre)@Nullable
    Button mBtnUpdateAdre;
    @BindView(R.id.address_et)@Nullable
    EditText mAdressEt;

    @BindView(R.id.phone_layout)@Nullable
    LinearLayout mPohneLayout;
    @BindView(R.id.phone_et)@Nullable
     EditText mPohneEt;
    @BindView(R.id.btn_update_phone)@Nullable Button mBtnUpdatePhone;


    @BindView(R.id.courses_layout)@Nullable LinearLayout mCoursesLayout;
    @BindView(R.id.mulitple_courses)@Nullable
    MultiAutoCompleteTextView mMutluplecourses;
    @BindView(R.id.btn_update_cour)@Nullable Button mBtnUpdateCour;
    private static   final String[] COURSES = new String[] {
            "Java", "C++", "Python", "Android ", "JavaEE","HTML","CSS","JavaScript","PHP","Laravel"
            ,"Math","Physics","calculs","Arabic","English"
    };

    @BindView(R.id.description_layout)@Nullable LinearLayout mDecriptionLayout;
    @BindView(R.id.description_et)@Nullable EditText mDesciptionEt;
    @BindView(R.id.btn_update_desc)@Nullable Button mBtnUpdateDesc;


    @BindView(R.id.btn_back_to_setting)@Nullable Button mBbtBackToSetting;

    FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        mAuth=FirebaseAuth.getInstance();

        initializeEvent();
        setAdapter();

        return root;
    }
    private  void setAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, COURSES);
        mMutluplecourses.setAdapter(adapter);
        mMutluplecourses.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
    private  void   initializeEvent(){

        mBbtBackToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainLayout.setVisibility(View.VISIBLE);
                mDecriptionLayout.setVisibility(View.GONE);
                mCoursesLayout.setVisibility(View.GONE);
                mPohneLayout.setVisibility(View.GONE);
                mAddressLinear.setVisibility(View.GONE);
                mBbtBackToSetting.setVisibility(View.GONE);
            }
        });
        mBtnUpdateDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainLayout.setVisibility(View.GONE);
                mDecriptionLayout.setVisibility(View.VISIBLE);
                mBbtBackToSetting.setVisibility(View.VISIBLE);

            }
        });
        mBtnUpdateCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCoursesLayout.setVisibility(View.VISIBLE);
                mMainLayout.setVisibility(View.GONE);
                mBbtBackToSetting.setVisibility(View.VISIBLE);

            }
        });
        mBtnUpdatePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mPohneLayout.setVisibility(View.VISIBLE);
                 mMainLayout.setVisibility(View.GONE);
                mBbtBackToSetting.setVisibility(View.VISIBLE);

            }
        });
        mBtn_update_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddressLinear.setVisibility(View.VISIBLE);
                mMainLayout.setVisibility(View.GONE);
                mBbtBackToSetting.setVisibility(View.VISIBLE);

            }
        });






        mBtnUpdateAdre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address=mAdressEt.getText().toString();
                if(ValidationData.isCorrectAddress(address)){
                    mAdressEt.setError(null);
                    if(((HomePageActivity)(getActivity())).userType.equals("2")) {
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.STUDENT_DB).
                                child(mAuth.getUid()).child("mAdress").setValue(address);
                    }
                    else{
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.TEACHER_DB).
                                child(mAuth.getUid()).child("mAdress").setValue(address);
                    }
                    Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_LONG).show();
                }else{
                    mAdressEt.setError("Input Valid adress Please!");
                }

            }
        });

        mBtnUpdatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=mPohneEt.getText().toString();
                if(ValidationData.isCorrectPhone(phone)){
                    mPohneEt.setError(null);
                    if(((HomePageActivity)(getActivity())).userType.equals("2")) {
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.STUDENT_DB).
                                child(mAuth.getUid()).child("mPhone").setValue(phone);
                    }
                    else{
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.TEACHER_DB).
                                child(mAuth.getUid()).child("mPhone").setValue(phone);
                    }
                    Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_LONG).show();
                }else{
                    mAdressEt.setError("Input Valid phone number Please!");
                }
            }
        });
        mBtnUpdateCour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courser=mMutluplecourses.getText().toString();
                if(ValidationData.isCorrectCources(courser)){
                    mMutluplecourses.setError(null);
                    if(((HomePageActivity)(getActivity())).userType.equals("2")) {
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.STUDENT_DB).
                                child(mAuth.getUid()).child("mCources").setValue(courser);
                    }
                    else{
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.TEACHER_DB).
                                child(mAuth.getUid()).child("mCources").setValue(courser);
                    }
                    Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_LONG).show();
                }else{
                    mAdressEt.setError("Input Valid courses Please!");
                }
            }

        });
        mBtnUpdateDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courser=mDesciptionEt.getText().toString();
                if(ValidationData.isCorrectDesciption(courser)){
                    mMutluplecourses.setError(null);
                    if(((HomePageActivity)(getActivity())).userType.equals("2")) {
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.STUDENT_DB).
                                child(mAuth.getUid()).child("mDescritption").setValue(courser);
                    }
                    else{
                        FirebaseDatabase.getInstance().getReference().child(AddingReadingData.TEACHER_DB).
                                child(mAuth.getUid()).child("mDescritption").setValue(courser);
                    }
                    Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_LONG).show();
                }else{
                    mAdressEt.setError("Input Valid Description Please!");
                }
            }

        });


    }



}
