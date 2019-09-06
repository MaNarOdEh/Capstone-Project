package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileStudentAdapter;
import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileTeacherAdapter;
import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.Validation.ValidationData;
import com.example.captonesecondstage.ui.Activity.HomePageActivity;
import com.example.captonesecondstage.ui.Activity.ProfileStudent_ParentsActivity;
import com.example.captonesecondstage.ui.Activity.ProfileTeacherActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


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
    ArrayList<Students>mStudents=new ArrayList<>();
    ArrayList<Teachers>mTeachers=new ArrayList<>();
    Timer searchScheduleTimer = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_search_page_framgents, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);


        search();
        initiliaeAdb();
        initializeFireBase();
        initializeEvent();

        return root;
    }

    private void search() {
      mSearchEdit.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void afterTextChanged(Editable editable) {
              if(editable.toString().length()>=1){
                  setSearchScheduleTimer();
              }else{
                  setAdapterRecycle();
              }

          }
      });
    }
    private void cancelTimer(){
        if (searchScheduleTimer != null){
            searchScheduleTimer.cancel();
            searchScheduleTimer.purge();
            searchScheduleTimer = null;
        }
    }
    private  void setSearchScheduleTimer(){
        cancelTimer();
        searchScheduleTimer = new Timer("search_scheduler");
        searchScheduleTimer.schedule(new TimerTask() {
            @Override
            public void run() {
             getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        performSearch();

                    }
                });
            }
        }, 1500);
    }
    public void performSearch(){
        if(mStudents.size()!=0){
            ArrayList<Students>students=new ArrayList<>();
            for(int i=0;i<mStudents.size();i++){
                if(mStudents.get(i).getmUserName().indexOf(mSearchEdit.getText().toString())>0){
                    students.add(mStudents.get(i));
                    Log.e("SSSSS",mStudents.get(i).getmUserName()) ;
                }
            }
            if(students.size()>0) {
                ProfileStudentAdapter profileAdapter = new ProfileStudentAdapter(students, SearchPageFramgents.this);
                mRandomSuggestionProfile.setAdapter(profileAdapter);

                mStudents = students;
            }

        }if(mTeachers.size()!=0){
            ArrayList<Teachers>teachers=new ArrayList<>();
            for(int i=0;i<mTeachers.size();i++){
                if(mTeachers.get(i).getmUserName().indexOf(mSearchEdit.getText().toString())>0){
                    teachers.add(mTeachers.get(i));
                }
            }
            if(teachers.size()>0) {
                ProfileTeacherAdapter profileAdapter = new ProfileTeacherAdapter(teachers, SearchPageFramgents.this);
                mRandomSuggestionProfile.setAdapter(profileAdapter);
                mTeachers = teachers;
            }


        }

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
        intent.putExtra(CommnuicationBetweenActivities.PROFILE_TEACHER_ACTIVITY_INTENT,mTeachers.get(position));
        startActivity(intent);

    }

    @Override
    public void onProfileStudentClicked(int position) {
        Intent intent=new Intent(getActivity(),ProfileStudent_ParentsActivity.class);
        intent.putExtra(CommnuicationBetweenActivities.PROFILE_STUDENTS_ACTVITVITY_INTENT,mStudents.get(position));
        startActivity(intent);
    }
    private void getAllStudents(){
        if(((HomePageActivity) getActivity()).checkConnection()) {
            mStudents=new ArrayList<>();
            FirebaseDatabase.getInstance().getReference().child(CommnuicationBetweenActivities.STUDENT_DB)
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
           mTeachers=new ArrayList<>();
           FirebaseDatabase.getInstance().getReference().child(CommnuicationBetweenActivities.TEACHER_DB)
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
