package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.captonesecondstage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplachActivity extends AppCompatActivity {

    @BindView(R.id.btn_login)@Nullable()
    Button mBtnLogIn;
    @BindView(R.id.btn_signUp)@Nullable()
    Button mBtnSignUp;
    @BindView(R.id.btn_aboutUs)@Nullable()
    Button mBtnAboutUs;
    public static final String KEY_RESULT="LOG_SIGN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        initializeEvents();
    }

    private void initializeEvents() {
        mBtnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SplachActivity.this,MainActivity.class);
                intent.putExtra(KEY_RESULT,"0");
                startActivity(intent);
            }
        });
        mBtnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SplachActivity.this,MainActivity.class);
                intent.putExtra(KEY_RESULT,"1");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
