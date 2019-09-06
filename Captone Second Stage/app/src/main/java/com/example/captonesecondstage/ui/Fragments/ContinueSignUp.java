package com.example.captonesecondstage.ui.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.DataBase.AddingReadingData;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.Validation.ValidationData;
import com.example.captonesecondstage.ui.Activity.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
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
    @BindView(R.id.main_layout)@Nullable()
    LinearLayout mMainLayout;
    @BindView(R.id.progress_circular)@Nullable()
    ProgressBar mProgressCircular;
    Dialog dialog;
    private static  final int RESULT_UPLOPAD_FROM_GALLERY=44;
    private static final int RESULT_IMAGE_CAPTURE = 22;
    Bitmap bitmap;

    ArrayList<String>data=new ArrayList<>();
    FirebaseAuth mAuth;

    private static   final String[] COURSES = new String[] {
            "Java", "C++", "Python", "Android ", "JavaEE","HTML","CSS","JavaScript","PHP","Laravel"
            ,"Math","Physics","calculs","Arabic","English"
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
            data=bundle.getStringArrayList(AddingReadingData.DATACREATEACCOUNTS_MAIN_ACTIVITY);
            mUserNameTv.setText(data.get(0));
        }else{
            ((MainActivity)getActivity()).showSignUpFragment();
        }
        //initialize Events
        initializeEvent();

        //setAdapters For Some Courses Name
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
        if(correct) {
            mProgressCircular.setVisibility(View.VISIBLE);
            if (((MainActivity) getActivity()).checkConnection()) {
                if (type.equals("Teacher")) {
                    Teachers teachers = new Teachers(data.get(0), data.get(1), data.get(2), phone, description, cources, address);
                    if(data.get(2).isEmpty()){
                        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestEmail()
                                .build();
                        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
                      firebaseAuthWithGoogle(account,teachers);
                    }else {

                        addTeacher(teachers);
                    }

                } else {
                    Students students = new Students(data.get(0), data.get(1), data.get(2)
                            , phone, description, cources, address);
                    if(data.get(2).isEmpty()){
                        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestEmail()
                                .build();
                        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
                        firebaseAuthWithGoogle(account,students);
                    }else {
                        addStudent(students);
                    }
                }
            }
        }else{
            ((MainActivity) getActivity()).showNoInternetConnectionFragment();
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
            bitmap = null;
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
            bitmap = (Bitmap) extras.get("data");
                if(bitmap!=null) {
                    mProfileImage.setImageBitmap(bitmap);
                }


        }
    }

    private  void storeImage(String mEmail){
        if(bitmap!=null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference profileImage = storageRef.child("ProfileImages");
            profileImage = profileImage.child("images/"+mEmail+".jpg");
            //ProfileImages/images/mEmail.jpg

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = profileImage.putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    ( (MainActivity) getActivity()). showSnackBar(exception.getMessage());
                    ( (MainActivity) getActivity()).goToTheHomePage();

                    mProgressCircular.setVisibility(View.GONE);
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ( (MainActivity) getActivity()).goToTheHomePage();

                    mProgressCircular.setVisibility(View.GONE);
                }
            });
        }else{
            ( (MainActivity) getActivity()).goToTheHomePage();

        }
    }
    private void addTeacher(Teachers teachers){

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(teachers.getmEmail(),teachers.getmPassword()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            rootRef.child(AddingReadingData.TEACHER_DB).child(mAuth.getUid())
                                    .setValue(teachers).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        rootRef.child(AddingReadingData.ALL_TECH).child(teachers.getmUserName()).setValue("1");
                                        storeImage(teachers.getmEmail());
                                    }else{
                                        ( (MainActivity) getActivity()). showSnackBar(task.getException()+" ");
                                    }
                                }
                            });
                        }else {
                            ( (MainActivity) getActivity()).    throwException(task);
                        }
                    }
                });

    }
    private void addStudent(Students students){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(students.getmEmail(),students.getmPassword()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            rootRef.child(AddingReadingData.STUDENT_DB).child(mAuth.getUid())
                                    .setValue(students).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        rootRef.child(AddingReadingData.ALL_TECH).child(students.getmUserName()).setValue("2");
                                        storeImage(students.getmEmail());
                                    }else{
                                        ( (MainActivity) getActivity()). showSnackBar(task.getException()+" ");
                                    }
                                }
                            });
                        }else {
                            ( (MainActivity) getActivity()).    throwException(task);
                        }
                    }
                });

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct,Students students) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    rootRef.child(AddingReadingData.STUDENT_DB).child(mAuth.getUid())
                            .setValue(students).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                rootRef.child(AddingReadingData.ALL_TECH).child(students.getmUserName()).setValue("2");
                               // acct.getPhotoUrl();
                                storeImage(students.getmEmail());
                            }else{
                                ( (MainActivity) getActivity()). showSnackBar(task.getException()+" ");
                            }
                        }
                    });
                }else{
                    ( (MainActivity) getActivity()).    throwException(task);
                }

            }
        });
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct,Teachers teachers) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    rootRef.child(AddingReadingData.STUDENT_DB).child(mAuth.getUid())
                            .setValue(teachers).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                rootRef.child(AddingReadingData.ALL_TECH).child(teachers.getmUserName()).setValue("2");
                              storeImage(teachers.getmEmail());
                            }else{
                                ( (MainActivity) getActivity()). showSnackBar(task.getException()+" ");
                            }
                        }
                    });
                }else{

                }

            }
        });
    }

}
