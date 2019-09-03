package com.example.captonesecondstage.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Fragments.LogInFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        FragmentManager fragmentManager=getSupportFragmentManager();
        LogInFragment fragment=new LogInFragment();
        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.login_frame,fragment).commit();
    }
}
