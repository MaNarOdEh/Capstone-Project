package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.captonesecondstage.Class.Notification;
import com.example.captonesecondstage.Class.RecycleAdpaters.NotificationAdapter;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.ProfileTeacherActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationFragments extends Fragment implements NotificationAdapter.OnDeleteClicked,NotificationAdapter.OnNotificationClicked {
    @BindView(R.id.recycle_notification)@Nullable
    RecyclerView mRecycleNotification;

    ArrayList<Notification> mNotificationList;

    NotificationAdapter mNotificationAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_notification_fragments, container, false);

        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        initializeEvent();
        setNotificationAdapter();

        return root;
    }

    private void setNotificationAdapter() {
        mNotificationList=new ArrayList<>();
        mNotificationList.add(new Notification());
        mNotificationList.add(new Notification());
        mNotificationList.add(new Notification());
        mNotificationList.add(new Notification());

        mNotificationAdapter=new NotificationAdapter(mNotificationList,this,this);
         mRecycleNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleNotification.setAdapter(mNotificationAdapter);

    }

    private void initializeEvent() {

    }


    @Override
    public void onDeleteClicked(int position) {


    }

    @Override
    public void onNotificationClicked(int position) {
        startActivity(new Intent(getContext(), ProfileTeacherActivity.class));
    }
}
