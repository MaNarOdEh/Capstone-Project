package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.captonesecondstage.Class.Internet_connection.ConnectivityReceiver;
import com.example.captonesecondstage.Class.Internet_connection.MyApplication;
import com.example.captonesecondstage.Class.Students;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Fragments.FavoriteFragments;
import com.example.captonesecondstage.ui.Fragments.NoInternetConnectionFragment;
import com.example.captonesecondstage.ui.Fragments.NotificationFragments;
import com.example.captonesecondstage.ui.Fragments.SearchPageFramgents;
import com.example.captonesecondstage.ui.Fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomePageActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    @BindView(R.id.frame_container)
    FrameLayout mFrameContainer;
    @BindView(R.id.navigation_bottom_container)
    BottomNavigationView mNavigationBottomContainer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    FirebaseAuth mAuth;
   public String userType="";
    public static final String USER_TYPE="USERTYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        mAuth=FirebaseAuth.getInstance();
        initializeEvent();

        MyApplication.getInstance().setConnectivityListener(this);

        SharedPreferences prefs = getSharedPreferences(USER_TYPE, Context.MODE_PRIVATE);
         userType = prefs.getString("USERTYPES", "");//""-Empty String is the default value.

        //set My Toolbar as aSupport ActionBar
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        showSearchPageFragments();
      /*  int app_widget= AppWidgetManager.INVALID_APPWIDGET_ID;
        Paper.init(this);
        Paper.book().write("INGREDIENTS","MANARODEH");
        Intent intent_meeting_update=new  Intent(HomePageActivity.this, MyWidget.class);
        intent_meeting_update.setAction(MyWidget.UPDATE_MEETING_ACTION);
        sendBroadcast(intent_meeting_update);*/
        setTitle("");
        setSupportActionBar(mToolbar);
    }

        public ArrayList<Students>getStudentsAdapters(){
                return new ArrayList<Students>();
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
    public void ShowNoInternetConnection(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        NoInternetConnectionFragment fragment=new NoInternetConnectionFragment();
        fragmentManager.beginTransaction().addToBackStack("disconnect").replace(R.id.frame_container,fragment).commit();
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
    // Method to manually check connection status
    public  boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        return isConnected;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(checkConnection()){
            showSearchPageFragments();
        }
    }
}
