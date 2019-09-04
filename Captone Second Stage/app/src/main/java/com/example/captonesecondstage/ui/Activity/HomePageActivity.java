package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.captonesecondstage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {
    @BindView(R.id.frame_container)
    FrameLayout mFrameContainer;
    @BindView(R.id.navigation_bottom_container)
    BottomNavigationView mNavigationBottomContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        initializeEvent();
    }

    private void initializeEvent() {
        mNavigationBottomContainer.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id= item.getItemId();
               switch (id){
                   case R.id.navigation_home:
                       break;
                   case R.id.navigation_favourite:
                       break;
                   case R.id.navigation_settings:
                       break;
                   case R.id.navigation_notification:
                       break;
               }

                return false;
            }
        });
    }
}
