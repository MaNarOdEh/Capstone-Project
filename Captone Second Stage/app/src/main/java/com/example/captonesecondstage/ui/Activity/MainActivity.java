package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ScrollView;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Fragments.ContinueSignUp;
import com.example.captonesecondstage.ui.Fragments.LogInFragment;
import com.example.captonesecondstage.ui.Fragments.SignUpFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_scrollView) ScrollView mMainScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        showLoginFragment();

    }
    public void showLoginFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        LogInFragment fragment=new LogInFragment();
        fragmentManager.beginTransaction().addToBackStack("Login").replace(R.id.loginSignUp_frame,fragment).commit();
    }
    public void showSignUpFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        SignUpFragment fragment=new SignUpFragment();
        fragmentManager.beginTransaction().addToBackStack("Sign").replace(R.id.loginSignUp_frame,fragment).commit();
    }
    public void showContinueSignUpFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        ContinueSignUp fragment=new ContinueSignUp();
        fragmentManager.beginTransaction().addToBackStack("CSign").replace(R.id.loginSignUp_frame,fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
