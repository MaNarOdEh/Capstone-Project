package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.captonesecondstage.Class.RecycleAdpaters.CoursesAdapter;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.R;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileTeacherActivity extends AppCompatActivity {

    @BindView(R.id.add_fav_img)@Nullable()
    ImageView mAddFavImg;
    @BindView(R.id.star_img_f)@Nullable()
    ImageView mStarImageF;
    @BindView(R.id.star_img_s)@Nullable()
    ImageView mStartImageS;
    @BindView(R.id.star_img_t)@Nullable()
    ImageView mStartImageT;
    @BindView(R.id.star_img_fourth)@Nullable()
    ImageView mStartImageFour;
    @BindView(R.id.star_img_fifth)@Nullable()
    ImageView mStartImageFifith;
    @BindView(R.id.circleImg_profile)@Nullable()
    CircleImageView mcircleImgProfile;
    @BindView(R.id.name_txt)@Nullable()
    TextView mNameTxt;
    @BindView(R.id.phone_txt)@Nullable()
    TextView mPhoneTxt;
    @BindView(R.id.aboutUs_txt)@Nullable()
    TextView mAboutUsTxt;
    @BindView(R.id.btn_call)@Nullable()
    Button mBtnCall;
    @BindView(R.id.btn_sms)@Nullable()
    Button mBtnSMS;
    @BindView(R.id.recycle_courses)@Nullable()
    RecyclerView mRecycleCourses;
    Teachers mTeachers;
    int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_teacher);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        Intent intent=getIntent();
        if(intent==null){
            finish();
        }else{
            mTeachers=intent.getParcelableExtra(CommnuicationBetweenActivities.PROFILE_TEACHER_ACTIVITY_INTENT);
            val=intent.getIntExtra(CommnuicationBetweenActivities.WATCH_MODE,0);
        }
        if(mTeachers==null){
            finish();
        }
        //initialize Values
        initialize();
        //initialize Events
        initializeEvent();
        //setAdapters
        setAdapterRecycle();
    }
    private  void initialize(){
        if(mTeachers!=null) {
            mNameTxt.setText(mTeachers.getmUserName());
            mPhoneTxt.setText(mTeachers.getmPhone());
            mAboutUsTxt.setText(mTeachers.getmDescritption() + "\n" + mTeachers.getmAdress());
            Picasso.get().load(mTeachers.getmImageUrl())
                    .placeholder(R.drawable.teachers)
                    .error(R.drawable.teachers)
                    .into(mcircleImgProfile);
        }
    }

    private void initializeEvent() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycleCourses.setHasFixedSize(true);

        // use a linear layout manager
        mRecycleCourses.setLayoutManager(new GridLayoutManager(this, 3));


        String myDataset[]=mTeachers.getmCources().trim().split(",");
        // specify an adapter (see also next example)
        CoursesAdapter coursesAdapter = new CoursesAdapter(myDataset);
        mRecycleCourses.setAdapter(coursesAdapter);

    }
    private void setAdapterRecycle() {
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val==1){
                    Toast.makeText(ProfileTeacherActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();
                }else {
                    String phone = mTeachers.getmPhone();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(CommnuicationBetweenActivities.CALL, phone, null));
                    startActivity(intent);
                }
            }
        });
        mBtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(val==1) {
                    Toast.makeText(ProfileTeacherActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();

                }else{
                    String number = mTeachers.getmPhone();  // The number on which you want to send SMS
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts(CommnuicationBetweenActivities.SMS, number, null)));
                }

            }
        });
    }
}
