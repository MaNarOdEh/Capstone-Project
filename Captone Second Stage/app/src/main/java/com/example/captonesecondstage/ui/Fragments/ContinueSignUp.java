package com.example.captonesecondstage.ui.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
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

import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.Validation.ValidationData;
import com.example.captonesecondstage.ui.Activity.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContinueSignUp extends Fragment {
    @BindView(R.id.profile_image) @Nullable()CircleImageView mProfileImage;
    @BindView(R.id.user_name_tv) @Nullable()TextView mUserNameTv;
    @BindView(R.id.user_phone_et) @Nullable()EditText mUserPhoneEt;
    @BindView(R.id.address_et) @Nullable()EditText mAdressEt;
    @BindView(R.id.description_et) @Nullable()EditText mDescriptionEt;
    @BindView(R.id.user_type_spinner) @Nullable()Spinner mUserTypeSpinner;
    @BindView(R.id.autoCompleteTv_courses)@Nullable() MultiAutoCompleteTextView  mCoursesAutoComplete;
    @BindView(R.id.signUp_btn)@Nullable() Button mSignUpBtn;
    @BindView(R.id.image_upload) @Nullable()ImageView mImageUpload;
    Dialog dialog;
    private static  final int RESULT_UPLOPAD_FROM_GALLERY=44;
    private static final int RESULT_IMAGE_CAPTURE = 22;

    ArrayList<String>data=new ArrayList<>();

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
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            data=bundle.getStringArrayList(MainActivity.DATACREATEACCOUNTS);
        }
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
              dialog.show();

            }
        });
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();

            }
        });
        dialog  = new Dialog(getActivity());
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_upload_capture_image);
        dialog.findViewById(R.id.btn_upload_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.take_photo_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPremissionTakePhotoAndCaptureImage();
                dialog.dismiss();
            }
        });
    }

    private  void setAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, COURSES);
        mCoursesAutoComplete.setAdapter(adapter);
        mCoursesAutoComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }
    private void checkData(){
        String phone=mUserPhoneEt.getText().toString(),
                address=mAdressEt.getText().toString(),
                 description=mDescriptionEt.getText().toString(),
                 cources=mCoursesAutoComplete.getText().toString(),
        type=mUserTypeSpinner.getItemAtPosition(mUserTypeSpinner.getSelectedItemPosition()).toString();
        boolean correct=true;
        if(!ValidationData.isCorrectPhone(phone)){
            mUserPhoneEt.setError("Your Phone number must be between 6 numbers and 10 ");
            correct=false;
        }else{
            mUserPhoneEt.setError(null);
        }if(!ValidationData.isCorrectAddress(address)){
            mAdressEt.setError("Please, it should be at least 3 letters or digits");
            correct=false;
        }else{
            mAdressEt.setError(null);
        }if(!ValidationData.isCorrectDesciption(description)){
            mDescriptionEt.setError("your description must be at least 8 character");
            correct=false;
        }else{
            mDescriptionEt.setError(null);
        }if(!ValidationData.isCorrectCources(cources)){
            mCoursesAutoComplete.setError("Input at least one courses!");
            correct=false;
        }else{
            mCoursesAutoComplete.setError(null);
        }
        if(correct){
            if(type.equals("Teacher")) {
                Teachers teachers=new Teachers(data.get(0),data.get(1),data.get(2),phone,description,cources,address);
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Teachers");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(teachers.getmUserName())) {
                            // run some code..this user name already taken before!!

                        }else{

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //dbRef.child(teachers.getmUserName());
            }else{
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Students_Parents");

            }
        }
    }
    private  void openGallery(){
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                RESULT_UPLOPAD_FROM_GALLERY);

    }
    private void checkPremissionTakePhotoAndCaptureImage(){
        if ( ContextCompat.checkSelfPermission( getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION )
                != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},1);
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, RESULT_IMAGE_CAPTURE);
        }else {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, RESULT_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if(requestCode==RESULT_UPLOPAD_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
          Bitmap  bitmap = null;
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                if(bitmap!=null) {
                    mProfileImage.setImageBitmap(bitmap);
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(requestCode== RESULT_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
          Bitmap  bitmap = (Bitmap) extras.get("data");
                if(bitmap!=null) {
                    mProfileImage.setImageBitmap(bitmap);
                }


        }
    }
}
