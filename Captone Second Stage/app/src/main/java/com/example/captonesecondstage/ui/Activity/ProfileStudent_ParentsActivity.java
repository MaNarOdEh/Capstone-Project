package com.example.captonesecondstage.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.captonesecondstage.R;

import butterknife.ButterKnife;

public class ProfileStudent_ParentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student__parents);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
    }
}
