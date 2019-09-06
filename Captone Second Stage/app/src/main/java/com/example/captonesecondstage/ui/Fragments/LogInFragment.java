package com.example.captonesecondstage.ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInFragment extends Fragment {
    @BindView(R.id.user_name_et)@Nullable() EditText mUserNameEt;
    @BindView(R.id.password_ed)@Nullable()EditText mPasswordEd;
    @BindView(R.id.remember_cb)@Nullable() CheckBox mRemmeberCb;
    @BindView(R.id.forget_password_btn)@Nullable() Button mForgetPasswordBtn;
    @BindView(R.id.login_btn)@Nullable() Button mLogin;
    @BindView(R.id.create_account_tv)@Nullable() TextView mCreateAccountTv;
    @BindView(R.id.gmail_floatBtn)@Nullable() FloatingActionButton mGmailFloatBtn;
    @BindView(R.id.face_floatBtn)@Nullable() FloatingActionButton mFaceBtn;
    @BindView(R.id.progress_circular)@Nullable() ProgressBar mProgressCircular;
    @BindView(R.id.sign_in_button)@Nullable()
    SignInButton mSign_in_button;
    GoogleSignInClient mGoogleSignInClient;
    private  static final int RC_SIGN_IN=3;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_log_in, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);
        mAuth=FirebaseAuth.getInstance();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        SharedPreferences prefs = getActivity().getSharedPreferences(CommnuicationBetweenActivities.SHARED_PREF_MY_PREFS_NAME, Context.MODE_PRIVATE);
        String email = prefs.getString(CommnuicationBetweenActivities.SHARED_PREF_EMAIL, "");//""-Empty String is the default value.
        String pass = prefs.getString(CommnuicationBetweenActivities.SHARED_PREF_PASS, ""); //""-Empty String is the default value.

        if(!email.isEmpty()){
            mRemmeberCb.setChecked(true);
        }
        mPasswordEd.setText(pass);
        mUserNameEt.setText(email);
        //initialize Events
        initializeEvent();

        return  root;
    }

    private void initializeEvent() {
        mCreateAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showSignUpFragment();
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressCircular.setVisibility(View.VISIBLE);
                checkValidData();
              // startActivity(new Intent(getActivity(), HomePageActivity.class));
            }
        });
        mForgetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showForgetPasswordFragments();
            }
        });
        mSign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mGmailFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSign_in_button.setPressed(true);
                signIn();
                mProgressCircular.setVisibility(View.VISIBLE);

            }
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void checkValidData(){
        String email=mUserNameEt.getText().toString(),
                password=mPasswordEd.getText().toString();
        boolean correct=true;
        if(email==null||email.isEmpty()){
            mUserNameEt.setError("Input Your Email Please!!");
            mProgressCircular.setVisibility(View.GONE);
            correct=false;
        }else{
            mUserNameEt.setError(null);
        }if(password==null||password.isEmpty()){
            mPasswordEd.setError("Input Your Password Please!!");
            mProgressCircular.setVisibility(View.GONE);
            correct=false;
        }else{
            mPasswordEd.setError(null);
        }
        if(correct) {
            if (((MainActivity) getActivity()).checkConnection()) {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (mRemmeberCb.isChecked()) {
                                editSharedPreference(email, password);
                            } else {
                                editSharedPreference("", "");
                            }
                            getUserType();

                        } else {
                            ((MainActivity) getActivity()).throwException(task);
                            mProgressCircular.setVisibility(View.GONE);
                        }
                    }
                });
            }
            else{
                ((MainActivity) getActivity()).showNoInternetConnectionFragment();
            }
        }

    }
    private  void editSharedPreference(String email,String pass){
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(CommnuicationBetweenActivities.SHARED_PREF_MY_PREFS_NAME,Context.MODE_PRIVATE).edit();
        editor.putString(CommnuicationBetweenActivities.SHARED_PREF_EMAIL, email);
        editor.putString(CommnuicationBetweenActivities.SHARED_PREF_PASS, pass);
        editor.apply();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if(account!=null) {
                mAuth.fetchSignInMethodsForEmail(account.getEmail()).
                        addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                                if(task.isSuccessful()){
                                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                                    mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                getUserType();

                                            }else{
                                                ((MainActivity)getActivity()).showSnackBar(task.getException()+"");

                                            }

                                        }
                                    });
                                }else{
                                    ((MainActivity)getActivity()).showSnackBar("You Don't have accounts create one instead!");

                                }
                            }
                        });
            }        }
        catch (ApiException e) {
                         ((MainActivity)getActivity()).showSnackBar(e.getMessage());

        }
    }
    private void getUserType(){
        //check in teacher and student table to get studen's type after that you can get's
        //the other side to show in main screen

        // Get a reference to our posts
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(CommnuicationBetweenActivities.STUDENT_DB).child(mAuth.getUid()).exists()){
                  //  SharedPreferences prefs = getActivity().getSharedPreferences(USER_TYPE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(CommnuicationBetweenActivities.SHARED_PREF_USER_TYPE, Context.MODE_PRIVATE).edit();
                    editor.putString(CommnuicationBetweenActivities.SHARED_PREF_USERTYPES, "2");
                    editor.apply();
                }else{
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(CommnuicationBetweenActivities.SHARED_PREF_USER_TYPE, Context.MODE_PRIVATE).edit();
                    editor.putString(CommnuicationBetweenActivities.SHARED_PREF_USERTYPES, "1");
                    editor.apply();

                }
                mProgressCircular.setVisibility(View.GONE);
                ((MainActivity)getActivity()).goToTheHomePage();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
