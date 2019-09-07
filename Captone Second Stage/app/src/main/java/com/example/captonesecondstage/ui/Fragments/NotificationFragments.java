package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.captonesecondstage.Class.Notification;
import com.example.captonesecondstage.Class.RecycleAdpaters.NotificationAdapter;
import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.HomePageActivity;
import com.example.captonesecondstage.ui.Activity.ProfileTeacherActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationFragments extends Fragment implements NotificationAdapter.OnDeleteClicked,NotificationAdapter.OnNotificationClicked {
    @BindView(R.id.recycle_notification)@Nullable
    RecyclerView mRecycleNotification;

    ArrayList<Notification> mNotificationList;

    NotificationAdapter mNotificationAdapter;
    String userNAme="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_notification_fragments, container, false);

        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        initializeEvent();
        getAllNotifications();
        setNotificationAdapter();

        return root;
    }

    private void setNotificationAdapter() {
        mNotificationList=new ArrayList<>();
        mRecycleNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleNotification.setAdapter(mNotificationAdapter);

    }

    private void initializeEvent() {

    }


    @Override
    public void onDeleteClicked(int position) {


    }
    private void getAllNotifications(){

        if(((HomePageActivity)getActivity()).userName==null||((HomePageActivity)getActivity()).userName.isEmpty()){
            getUserName();
        }else {
            Toast.makeText(getContext(), ((HomePageActivity) getActivity()).userName, Toast.LENGTH_SHORT).show();
            FirebaseDatabase.getInstance().getReference(CommnuicationBetweenActivities.NOTIFICATIONST).child(HomePageActivity.userName).
                    addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Notification notification = snapshot.getValue(Notification.class);
                                mNotificationList.add(notification);
                            }
                            mNotificationAdapter = new NotificationAdapter(mNotificationList,
                                    NotificationFragments.this::onNotificationClicked, NotificationFragments.this::onDeleteClicked);
                            mRecycleNotification.setAdapter(mNotificationAdapter);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }
    }

    @Override
    public void onNotificationClicked(int position) {
        startActivity(new Intent(getContext(), ProfileTeacherActivity.class));
    }
    private  void getUserName(){
        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(CommnuicationBetweenActivities.TEACHER_DB).child(FirebaseAuth.getInstance().getUid()).exists()) {
                    userNAme = dataSnapshot.child(CommnuicationBetweenActivities.TEACHER_DB).child(FirebaseAuth.getInstance().getUid())
                            .child("mUserName").getValue().toString();
                    FirebaseDatabase.getInstance().getReference(CommnuicationBetweenActivities.NOTIFICATIONST).child(HomePageActivity.userName).
                            addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Notification notification = snapshot.getValue(Notification.class);
                                        mNotificationList.add(notification);
                                    }
                                    mNotificationAdapter = new NotificationAdapter(mNotificationList,
                                            NotificationFragments.this::onNotificationClicked, NotificationFragments.this::onDeleteClicked);
                                    mRecycleNotification.setAdapter(mNotificationAdapter);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }else{
                    userNAme = dataSnapshot.child(CommnuicationBetweenActivities.STUDENT_DB).child(FirebaseAuth.getInstance().getUid())
                            .child("mUserName").getValue().toString();
                    FirebaseDatabase.getInstance().getReference(CommnuicationBetweenActivities.NOTIFICATIONST).child(HomePageActivity.userName).
                            addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        Notification notification = snapshot.getValue(Notification.class);
                                        mNotificationList.add(notification);
                                    }
                                    mNotificationAdapter = new NotificationAdapter(mNotificationList,
                                            NotificationFragments.this::onNotificationClicked, NotificationFragments.this::onDeleteClicked);
                                    mRecycleNotification.setAdapter(mNotificationAdapter);


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
