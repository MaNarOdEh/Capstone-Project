package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Fragments.FavoriteFragments;
import com.example.captonesecondstage.ui.Fragments.NotificationFragments;
import com.example.captonesecondstage.ui.Fragments.SearchPageFramgents;
import com.example.captonesecondstage.ui.Fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {
    @BindView(R.id.frame_container)
    FrameLayout mFrameContainer;
    @BindView(R.id.navigation_bottom_container)
    BottomNavigationView mNavigationBottomContainer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    FirebaseAuth mAuth;
    String userType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        mAuth=FirebaseAuth.getInstance();
        initializeEvent();

        //set My Toolbar as aSupport ActionBar
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setTitle("");
        setSupportActionBar(mToolbar);
        showSearchPageFragments();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()==null){
            goToSplashScreen();
        }
    }

    public void showSearchPageFragments(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        SearchPageFramgents fragment=new SearchPageFramgents();
        fragmentManager.beginTransaction().addToBackStack("Search").replace(R.id.frame_container,fragment).commit();
    }
    public void showNotificationFragments(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        NotificationFragments fragment=new NotificationFragments();
        fragmentManager.beginTransaction().addToBackStack("notification").replace(R.id.frame_container,fragment).commit();
    }
    public void showSettingsFragments(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        SettingsFragment fragment=new SettingsFragment();
        fragmentManager.beginTransaction().addToBackStack("settings").replace(R.id.frame_container,fragment).commit();
    }
    public void showFavouriteFragments(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FavoriteFragments fragment=new FavoriteFragments();
        fragmentManager.beginTransaction().addToBackStack("favourite").replace(R.id.frame_container,fragment).commit();
    }
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 1){

        }else{
            super.onBackPressed();
        }
    }
    private void initializeEvent() {
        mNavigationBottomContainer.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id= item.getItemId();
               switch (id){
                   case R.id.navigation_home:
                       showSearchPageFragments();
                       break;
                   case R.id.navigation_favourite:
                       showFavouriteFragments();
                       break;
                   case R.id.navigation_settings:
                       showSettingsFragments();
                       break;
                   case R.id.navigation_notification:
                       showNotificationFragments();
                       break;
                   case R.id.navigation_signOut:
                       mAuth.signOut();
                       goToSplashScreen();
                       break;
               }updateNavigationBarState(item.getItemId());

                return false;
            }
        });
    }
    private void goToSplashScreen(){ startActivity(new Intent(this,SplachActivity.class)); }
    private void updateNavigationBarState(int actionId){
        Menu menu = mNavigationBottomContainer.getMenu();

        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            item.setChecked(item.getItemId() == actionId);
        }
    }
}
