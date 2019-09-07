package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.captonesecondstage.Class.NotificationHelper;
import com.example.captonesecondstage.R;
import com.google.firebase.auth.FirebaseAuth;

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
    FirebaseAuth mAuth;
    private static final String CHANNEL_ID="PRIVATE_TEACHER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_splach);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        createNotificationChannel();
        mAuth=FirebaseAuth.getInstance();
        initializeEvents();

    }


    private void initializeEvents() {
        mBtnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationHelper.display_Notification(getApplicationContext(),getString(R.string.welcome_notification),getString(R.string.welcome_notifcation_body));

            }
        });
        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SplachActivity.this,MainActivity.class);
                intent.putExtra(KEY_RESULT,"0");
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                    View v=findViewById(R.id.shared_image);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(SplachActivity.this, v, "profile");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    // Swap without transition
                }
            }
        });
        mBtnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SplachActivity.this,MainActivity.class);
                intent.putExtra(KEY_RESULT,"1");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                    View v=findViewById(R.id.shared_image);
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(SplachActivity.this, v, "profile");
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                    // Swap without transition
                }
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
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(this,HomePageActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
