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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.captonesecondstage.Class.Internet_connection.ConnectivityReceiver;
import com.example.captonesecondstage.Class.Internet_connection.MyApplication;
import com.example.captonesecondstage.Class.Notification;
import com.example.captonesecondstage.Class.Students;

import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Fragments.FavoriteFragments;
import com.example.captonesecondstage.ui.Fragments.NoInternetConnectionFragment;
import com.example.captonesecondstage.ui.Fragments.NotificationFragments;
import com.example.captonesecondstage.ui.Fragments.SearchPageFramgents;
import com.example.captonesecondstage.ui.Fragments.SettingsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

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
    @BindView(R.id.float_profile) @NonNull FloatingActionButton  mFloatProfile;
    @BindView(R.id.progress_profile)@NonNull
    ProgressBar mProgressProfile;
    public static String userName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        mAuth=FirebaseAuth.getInstance();
        initializeEvent();
        getUserName();

        MyApplication.getInstance().setConnectivityListener(this);


        if(getSupportActionBar()!=null)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences prefs = getSharedPreferences(CommnuicationBetweenActivities.SHARED_PREF_USER_TYPE, Context.MODE_PRIVATE);
         userType = prefs.getString(CommnuicationBetweenActivities.SHARED_PREF_USERTYPES, "");//""-Empty String is the default value.

        //set My Toolbar as aSupport ActionBar
        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        showSearchPageFragments();
        setTitle("");
        setSupportActionBar(mToolbar);
    }

    private void getUserName() {
        //in this case we check if the user is already have an accounts or not!!
        if (userType.equals("1")) {
            FirebaseDatabase.getInstance().getReference().
                    child(CommnuicationBetweenActivities.TEACHER_DB).child(mAuth.getUid()).child("mUserName").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            userName = dataSnapshot.getValue().toString();
                            getToken();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    }
            );
        }else{
            FirebaseDatabase.getInstance().getReference().
                    child(CommnuicationBetweenActivities.STUDENT_DB).child(mAuth.getUid()).child("mUserName").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            userName = dataSnapshot.getValue().toString();
                            getToken();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    }
            );
        }
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
        mFloatProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressProfile.setVisibility(View.VISIBLE);
              //  Toast.makeText(HomePageActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference();
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(userType.equals("2")){
                           Students students=dataSnapshot.child(CommnuicationBetweenActivities.STUDENT_DB).child(mAuth.getUid()).getValue(Students.class);
                            Intent intent=new Intent(HomePageActivity.this,ProfileStudent_ParentsActivity.class);
                            intent.putExtra(CommnuicationBetweenActivities.PROFILE_STUDENTS_ACTVITVITY_INTENT,students);
                            intent.putExtra(CommnuicationBetweenActivities.WATCH_MODE,1);
                            startActivity(intent);

                        }else{
                            Teachers teachers=dataSnapshot.child(CommnuicationBetweenActivities.TEACHER_DB).child(mAuth.getUid()).getValue(Teachers.class);
                            Intent intent=new Intent(HomePageActivity.this,ProfileTeacherActivity.class);
                            intent.putExtra(CommnuicationBetweenActivities.PROFILE_TEACHER_ACTIVITY_INTENT,teachers);
                            intent.putExtra(CommnuicationBetweenActivities.WATCH_MODE,1);
                            startActivity(intent);

                        }
                        mProgressProfile.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        mNavigationBottomContainer.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id= item.getItemId();
               switch (id){
                   case R.id.navigation_home:
                       showSearchPageFragments();
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
    private void show_Profile(){
        if(userType.equals("1")){//teacher
            Intent intent=new Intent(this,ProfileStudent_ParentsActivity.class);

        }else{//students--parents
            Intent intent=new Intent(this,ProfileStudent_ParentsActivity.class);


        }
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
    public void getToken(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                String token=task.getResult().getToken();
                save_token(token);
            }
        });
    }
    private void save_token(String token){
        FirebaseDatabase.getInstance().getReference().child("TOKENS").child(userName).child("token").setValue(token).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(Main_Activity.this,"done",Toast.LENGTH_LONG).show();
                }else{
                    //Toast.makeText(Main_Activity.this,"Some thing wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void storeNorifications(Notification notification,String user_name){
        FirebaseDatabase.getInstance().getReference().child(CommnuicationBetweenActivities.NOTIFICATIONST)
                .child(user_name).push().setValue(notification).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(HomePageActivity.this, "Successfully Done", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HomePageActivity.this, "Something Wrong Happen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //there is no delete notifications MANAR??:::
}
