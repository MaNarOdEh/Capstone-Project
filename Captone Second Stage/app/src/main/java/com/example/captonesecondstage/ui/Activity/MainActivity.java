package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ScrollView;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Fragments.ContinueSignUp;
import com.example.captonesecondstage.ui.Fragments.LogInFragment;
import com.example.captonesecondstage.ui.Fragments.SignUpFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @BindView(R.id.main_scrollView)@Nullable() ScrollView mMainScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        mAuth= FirebaseAuth.getInstance();
        // Write a message to the database
       /* FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");*/

        Intent intent=getIntent();
        if(intent!=null){
           String res= intent.getStringExtra(SplachActivity.KEY_RESULT);
           if(res.equals("1")){
               showLoginFragment();

           }else {
               showSignUpFragment();
           }
        }else {

            showLoginFragment();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth!=null){
            goToTheHomePage();
        }
    }
    private  void goToTheHomePage(){
        startActivity(new Intent(this,HomePageActivity.class));
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
        if(getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }else{
            super.onBackPressed();
        }
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
