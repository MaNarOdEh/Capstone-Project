package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileStudentAdapter;
import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileTeacherAdapter;
import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.DataBase.AddingReadingData;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.HomePageActivity;
import com.example.captonesecondstage.ui.Activity.ProfileStudent_ParentsActivity;
import com.example.captonesecondstage.ui.Activity.ProfileTeacherActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPageFramgents extends Fragment implements ProfileTeacherAdapter.OnProfileClicked, ProfileStudentAdapter.OnProfileStudentClicked {
    @BindView(R.id.random_suggestion_profile)@Nullable()
    RecyclerView mRandomSuggestionProfile;
    @BindView(R.id.search_edit)@Nullable()
    EditText mSearchEdit;
    @BindView(R.id.adView)
    @Nullable
    AdView mAdView;
    FirebaseAuth mAuth;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseStorage mFirebaseStorage;
    ArrayList<Students>mStudents=new ArrayList<>();
    ArrayList<Teachers>mTeachers=new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_search_page_framgents, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);



        initiliaeAdb();
        initializeFireBase();
        initializeEvent();

        return root;
    }

    private void initiliaeAdb() {
        MobileAds.initialize(getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initializeFireBase() {
        mAuth=FirebaseAuth.getInstance();

    }

    private void initializeEvent() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRandomSuggestionProfile.setHasFixedSize(true);

        // use a linear layout manager
        mRandomSuggestionProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapterRecycle();

    }
    private void setAdapterRecycle() {
        if(((HomePageActivity)(getActivity())).userType.equals("1")){
            getAllStudents();
        }else{
            getAllTeachers();
        }
    }


    @Override
    public void onProfileClicked(int position) {
        Log.e("ERROROR","MUSTGOOO");
        Intent intent=new Intent(getActivity(),ProfileTeacherActivity.class);
        intent.putExtra("TEACHERINFO",mTeachers.get(position));
        startActivity(intent);

    }

    @Override
    public void onProfileStudentClicked(int position) {
        Intent intent=new Intent(getActivity(),ProfileStudent_ParentsActivity.class);
        intent.putExtra("STUDENTS",mStudents.get(position));
        startActivity(intent);
    }
    private void getAllStudents(){
        if(((HomePageActivity) getActivity()).checkConnection()) {
            FirebaseDatabase.getInstance().getReference().child(AddingReadingData.STUDENT_DB)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Students user = snapshot.getValue(Students.class);
                                try {
                                    StorageReference load = FirebaseStorage.getInstance().
                                            getReferenceFromUrl("ProfileImages/images/" + user.getmEmail() + ".jpg");

                                    load.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            user.setmImageUrl(uri.toString());
                                            //  Picasso.with(context).load(uri.toString()).into(imageView);
                                        }
                                    });
                                } catch (Exception e) {

                                }
                                mStudents.add(user);
                                ProfileStudentAdapter profileAdapter = new ProfileStudentAdapter(mStudents, SearchPageFramgents.this::onProfileStudentClicked);
                                mRandomSuggestionProfile.setAdapter(profileAdapter);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
        }else{
            ((HomePageActivity)getActivity()).ShowNoInternetConnection();
        }

    }
    private void getAllTeachers(){
       if( ((HomePageActivity) getActivity()).checkConnection()) {
           FirebaseDatabase.getInstance().getReference().child(AddingReadingData.TEACHER_DB)
                   .addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                               Teachers user = snapshot.getValue(Teachers.class);
                               try {
                                   StorageReference load = FirebaseStorage.getInstance().
                                           getReferenceFromUrl("ProfileImages/images/" + user.getmEmail() + ".jpg");

                                   load.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                       @Override
                                       public void onSuccess(Uri uri) {
                                           user.setmImageUrl(uri.toString());
                                           //  Picasso.with(context).load(uri.toString()).into(imageView);
                                       }
                                   });
                               } catch (Exception e) {

                               }
                               mTeachers.add(user);
                               ProfileTeacherAdapter profileAdapter = new ProfileTeacherAdapter(mTeachers, SearchPageFramgents.this::onProfileClicked);
                               mRandomSuggestionProfile.setAdapter(profileAdapter);

                           }
                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {
                       }
                   });
       }else{
           ((HomePageActivity)getActivity()).ShowNoInternetConnection();
       }
    }
}
