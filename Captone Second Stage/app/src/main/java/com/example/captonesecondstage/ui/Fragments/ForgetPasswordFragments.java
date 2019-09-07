package com.example.captonesecondstage.ui.Fragments;

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

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.Validation.ValidationData;
import com.example.captonesecondstage.ui.Activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ForgetPasswordFragments extends Fragment {
    @BindView(R.id.user_email_et)@Nullable()
    EditText mUserEmailEt;
    @BindView(R.id.login_btn)@Nullable()
    Button mBtnLogin;
    @BindView(R.id.message_txt)@Nullable()
    TextView mMessageTxt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.forget_password_fragments, container, false);
        ButterKnife.bind(this,root);
        ButterKnife.setDebug(true);

        initializeEvent();
        return root;
    }
    private  void initializeEvent(){
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUserEmailEt.getVisibility()==View.GONE){
                    ((MainActivity)getActivity()).showLoginFragment();
                }else {
                    String email = mUserEmailEt.getText().toString();
                    if (ValidationData.isCorrectEmail(email)) {
                        mUserEmailEt.setError(null);
                        if(((MainActivity) getActivity()).checkConnection()) {
                            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        mMessageTxt.setText(R.string.reset_password_link_send);
                                        mBtnLogin.setText(R.string.login);
                                        mUserEmailEt.setVisibility(View.GONE);
                                    } else {
                                        ((MainActivity) getActivity()).showSnackBar(getString(R.string.wrong_message));
                                    }
                                }
                            });
                        }else{
                            ((MainActivity) getActivity()).showNoInternetConnectionFragment();
                        }

                    } else {
                        mUserEmailEt.setError(getString(R.string.email_error));
                    }
                }
            }
        });
    }


}
