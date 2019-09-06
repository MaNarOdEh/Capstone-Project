package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ScrollView;

import com.example.captonesecondstage.Class.Internet_connection.ConnectivityReceiver;
import com.example.captonesecondstage.Class.Internet_connection.MyApplication;
import com.example.captonesecondstage.DataBase.AddingReadingData;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Fragments.ContinueSignUp;
import com.example.captonesecondstage.ui.Fragments.ForgetPasswordFragments;
import com.example.captonesecondstage.ui.Fragments.LogInFragment;
import com.example.captonesecondstage.ui.Fragments.NoInternetConnectionFragment;
import com.example.captonesecondstage.ui.Fragments.SignUpFragment;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    FirebaseAuth mAuth;
    @BindView(R.id.main_scrollView)@Nullable() ScrollView mMainScrollView;
    @BindView(R.id.main_layout)@Nullable()
    CoordinatorLayout mMainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        mAuth= FirebaseAuth.getInstance();
        MyApplication.getInstance().setConnectivityListener(this);




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

    public  void goToTheHomePage(){
        startActivity(new Intent(this,HomePageActivity.class));
    }

    public void showLoginFragment(){
        setBackgroundImages();
        FragmentManager fragmentManager=getSupportFragmentManager();
        LogInFragment fragment=new LogInFragment();
        fragmentManager.beginTransaction().addToBackStack("Login").replace(R.id.loginSignUp_frame,fragment).commit();
    }
    public void showSignUpFragment(){
        setBackgroundImages();
        FragmentManager fragmentManager=getSupportFragmentManager();
        SignUpFragment fragment=new SignUpFragment();
        fragmentManager.beginTransaction().addToBackStack("Sign").replace(R.id.loginSignUp_frame,fragment).commit();
    }
    public void showContinueSignUpFragment(String name,String email,String password){
        setBackGroundToNull();
        FragmentManager fragmentManager=getSupportFragmentManager();
        ArrayList<String>data=new ArrayList<>();
        data.add(name);
        data.add(email);
        data.add(password);
        Bundle bundle=new Bundle();
        bundle.putStringArrayList(AddingReadingData.DATACREATEACCOUNTS_MAIN_ACTIVITY,data);
        ContinueSignUp fragment=new ContinueSignUp();
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().addToBackStack("CSign").replace(R.id.loginSignUp_frame,fragment).commit();
    }
    public void showForgetPasswordFragments(){
        setBackGroundToNull();
        FragmentManager fragmentManager=getSupportFragmentManager();
        ForgetPasswordFragments fragment=new ForgetPasswordFragments();
        fragmentManager.beginTransaction().addToBackStack("forget").replace(R.id.loginSignUp_frame,fragment).commit();
    }
    public void showNoInternetConnectionFragment(){
        setBackGroundToNull();
        FragmentManager fragmentManager=getSupportFragmentManager();
        NoInternetConnectionFragment fragment=new NoInternetConnectionFragment();
        fragmentManager.beginTransaction().addToBackStack("DisConnect").replace(R.id.loginSignUp_frame,fragment).commit();
    }
    // Method to manually check connection status
    public  boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        return isConnected;
    }

    private void setBackgroundImages(){
        mMainScrollView.setBackgroundResource(R.drawable.background);

    }
    private void setBackGroundToNull(){
        mMainScrollView.setBackground(null);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
    public  void showSnackBar(String message){
        Snackbar snackbar = Snackbar
                .make(mMainLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public void throwException(@Nullable Task<AuthResult> task){
        try
        {
            throw task.getException();
        }
        // if user enters wrong email.
        catch (FirebaseAuthWeakPasswordException weakPassword)
        {

             showSnackBar("Weak password,Try Input Stronger password!");

        }
        // if user enters wrong password.
        catch (FirebaseAuthInvalidCredentialsException malformedEmail)
        {
          showSnackBar("malformed_email!");

        }
        catch (FirebaseAuthUserCollisionException existEmail)
        {
           showSnackBar("Exist email! The Input Email is already taken");

        }
        catch (Exception e)
        {
          showSnackBar(e.getMessage());

        }
    }
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }else{
            setBackgroundImages();
            super.onBackPressed();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            goToTheHomePage();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(checkConnection()){
            showLoginFragment();
        }
    }
}
