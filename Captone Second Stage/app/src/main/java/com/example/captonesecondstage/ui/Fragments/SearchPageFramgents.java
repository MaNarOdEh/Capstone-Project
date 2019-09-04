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
import android.widget.EditText;

import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileStudentAdapter;
import com.example.captonesecondstage.Class.RecycleAdpaters.ProfileTeacherAdapter;
import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.ProfileStudent_ParentsActivity;
import com.example.captonesecondstage.ui.Activity.ProfileTeacherActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchPageFramgents extends Fragment implements ProfileTeacherAdapter.OnProfileClicked, ProfileStudentAdapter.OnProfileStudentClicked {
    @BindView(R.id.random_suggestion_profile)@Nullable()
    RecyclerView mRandomSuggestionProfile;
    @BindView(R.id.search_edit)@Nullable()
    EditText mSearchEdit;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_search_page_framgents, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        initializeEvent();
        return root;
    }

    private void initializeEvent() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRandomSuggestionProfile.setHasFixedSize(true);

        // use a linear layout manager
        mRandomSuggestionProfile.setLayoutManager(new LinearLayoutManager(getContext()));


       /* ArrayList<Teachers> myDataset=new ArrayList<>();
        myDataset.add(new Teachers());
        myDataset.add(new Teachers());
        myDataset.add(new Teachers());
        myDataset.add(new Teachers());
        myDataset.add(new Teachers());
        myDataset.add(new Teachers());
         ProfileTeacherAdapter profileAdapter = new ProfileTeacherAdapter(myDataset,this);*/
        // specify an adapter (see also next example)
        ArrayList<Students> myDataset=new ArrayList<>();
        myDataset.add(new Students());
        myDataset.add(new Students());
        myDataset.add(new Students());
        myDataset.add(new Students());
        myDataset.add(new Students());
        myDataset.add(new Students());

        ProfileStudentAdapter profileAdapter = new ProfileStudentAdapter(myDataset,this);
        mRandomSuggestionProfile.setAdapter(profileAdapter);

    }
    private void setAdapterRecycle() {

    }


    @Override
    public void onProfileClicked(int position) {
        Log.e("ERROROR","MUSTGOOO");
        startActivity(new Intent(getActivity(), ProfileTeacherActivity.class));

    }

    @Override
    public void onProfileStudentClicked(int position) {
        startActivity(new Intent(getActivity(), ProfileStudent_ParentsActivity.class));
    }
}
