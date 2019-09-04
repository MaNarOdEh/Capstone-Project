package com.example.captonesecondstage.ui.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.captonesecondstage.R;

import butterknife.ButterKnife;

public class NotificationFragments extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_notification_fragments, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);
        return root;
    }


}
