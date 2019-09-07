package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileStudentAdapter;
import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileTeacherAdapter;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.ProfileStudent_ParentsActivity;
import com.example.captonesecondstage.ui.Activity.ProfileTeacherActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragments extends Fragment implements ProfileStudentAdapter.OnProfileStudentClicked,ProfileTeacherAdapter.OnProfileClicked {
    @BindView(R.id.recycle_Favorite)@Nullable()
    RecyclerView mRecycleFavorite;
    ArrayList<Teachers>mTeachers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_favorite_fragments, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);


        initializeEvent();
        setNotificationAdapter();
        return root;
    }
    private void setNotificationAdapter() {
        mTeachers=new ArrayList<>();
        mTeachers.add(new Teachers());
        mTeachers.add(new Teachers());
        mTeachers.add(new Teachers());
        mTeachers.add(new Teachers());
        mTeachers.add(new Teachers());
        mTeachers.add(new Teachers());
        ProfileTeacherAdapter mProfileStudentAdapter=new ProfileTeacherAdapter(mTeachers,this);
        mRecycleFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleFavorite.setAdapter(mProfileStudentAdapter);

    }

    private void initializeEvent() {

    }
    @Override
    public void onProfileClicked(int position) {
     //   Log.e("ERROROR","MUSTGOOO");
        startActivity(new Intent(getActivity(), ProfileTeacherActivity.class));

    }

    @Override
    public void onProfileStudentClicked(int position) {
        startActivity(new Intent(getActivity(), ProfileStudent_ParentsActivity.class));
    }

}
