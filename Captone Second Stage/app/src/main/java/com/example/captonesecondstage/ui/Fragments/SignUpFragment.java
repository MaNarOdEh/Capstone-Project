package com.example.captonesecondstage.ui.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.Validation.ValidationData;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment {
    @BindView(R.id.user_name_et)@Nullable()EditText mUserNameEt;
    @BindView(R.id.user_email_et)@Nullable()EditText mUserEmailEt;
    @BindView(R.id.password_ed)@Nullable()EditText mPasswordEd;
    @BindView(R.id.cfPassword_ed)@Nullable() EditText mcfPasswordEt;
    @BindView(R.id.signUp_btn)@Nullable() Button mSignUpBtn;
    @BindView(R.id.gmail_floatBtn)@Nullable() FloatingActionButton mGmailFloatBtn;
    @BindView(R.id.face_floatBtn)@Nullable()FloatingActionButton mFaceFloatBtn;
    @BindView(R.id.create_account_tv)@Nullable() TextView mCreateAccountTv;
    @BindView(R.id.sign_in_button)@Nullable()
    SignInButton mSign_in_button;
    GoogleSignInClient mGoogleSignInClient;
    private  final static int RC_SIGN_IN=3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        //initialize Events
        initializeEvent();

        return root;
    }

    private void initializeEvent() {
        mCreateAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showLoginFragment();
            }
        });
        mSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInput();

            }

        });
        mGmailFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSign_in_button.setPressed(true);
                createAccountsUsingGoogleAccounts();

            }
        });
        mSign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccountsUsingGoogleAccounts();
            }
        });

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void createAccountsUsingGoogleAccounts() {
        String userName=mUserNameEt.getText().toString();
        if(!ValidationData.isCorrectName(userName)){
            mUserNameEt.setError("Your name must al least have 3 char at the beginning,\nyou should input a user name before!!");
        }else{
            mUserNameEt.setError(null);
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(CommnuicationBetweenActivities.ALL_TECH).hasChild(userName) ){
                        // run some code..this user name already taken before!!
                        mUserNameEt.setError("Your Name Must Be Unique this name is already taken before!!");
                    }else{
                        signIn();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void validateInput() {
        String userName=mUserNameEt.getText().toString(),
                userEmail=mUserEmailEt.getText().toString(),
                password=mPasswordEd.getText().toString(),
                cpassword=mcfPasswordEt.getText().toString();
        boolean correct=true;
        if(!ValidationData.isCorrectName(userName)){
            mUserNameEt.setError(getString(R.string.name_wrong));
            correct=false;
        }else{
            mUserNameEt.setError(null);
        }if(!ValidationData.isCorrectEmail(userEmail)){
            mUserEmailEt.setError(getString(R.string.email_error));
            correct=false;
        }else{
            mUserNameEt.setError(null);
        }if(!ValidationData.isCorrectPassword(password)){
            mPasswordEd.setError(getString(R.string.password_eworng));
        }else{
            mPasswordEd.setError(null);
            if(!password.equals(cpassword)){
                mcfPasswordEt.setError(getString(R.string.not_equal));
                correct=false;
            }else{
                mcfPasswordEt.setError(null);
            }
        }
        if(correct) {
            if (((MainActivity) getActivity()).checkConnection()) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(CommnuicationBetweenActivities.ALL_TECH).hasChild(userName)) {
                            // run some code..this user name already taken before!!
                            mUserNameEt.setError(getString(R.string.user_name_taken_before));
                            ((MainActivity) getActivity()).showSnackBar(getString(R.string.user_name_taken_before));

                        } else {
                            mUserNameEt.setError(null);
                            ((MainActivity) getActivity()).showContinueSignUpFragment(userName, userEmail, cpassword);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
            else{
                ((MainActivity) getActivity()).showNoInternetConnectionFragment();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
            // Signed in successfully, show authenticated UI.
            FirebaseAuth.getInstance().fetchSignInMethodsForEmail(account.getEmail()).
                    addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                            if(task.isSuccessful()){
                                ((MainActivity)getActivity()).showContinueSignUpFragment(mUserNameEt.getText().toString(),account.getEmail(),"");


                            }else{
                                ((MainActivity)getActivity()).showSnackBar(getString(R.string.account_issues));

                            }
                        }
                    });
        } catch (ApiException e) {
            ((MainActivity)getActivity()).showSnackBar(e.getMessage());
        }
    }
}
