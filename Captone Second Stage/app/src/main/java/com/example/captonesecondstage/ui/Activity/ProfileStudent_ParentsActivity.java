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
import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.MyWidget;
import com.example.captonesecondstage.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class ProfileStudent_ParentsActivity extends AppCompatActivity {
    @BindView(R.id.add_fav_img)@Nullable()
    ImageView mAddFavImg;
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
    @BindView(R.id.btn_send_request)@Nullable()
    Button mBtnSendRequest;
    Students mStudents;
    int val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student__parents);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        Intent intent=getIntent();
        if(intent!=null){
            mStudents=intent.getParcelableExtra(CommnuicationBetweenActivities.PROFILE_STUDENTS_ACTVITVITY_INTENT);
            val=intent.getIntExtra(CommnuicationBetweenActivities.WATCH_MODE,0);
        }else{
            finish();
        }
        if(mStudents==null){
            finish();
        }
        //show Student Info
        showInfo();
        //initialize Events
        initializeEvent();
        //setAdapters
        setAdapterRecycle();

    }
    private  void showInfo() {
        if (mStudents != null) {
            mNameTxt.setText(mStudents.getmUserName());
            mPhoneTxt.setText(mStudents.getmPhone());
            mAboutUsTxt.setText(mStudents.getmDescritption() + "\n" +
                    mStudents.getmAdress());
            Picasso.get().load(mStudents.getmImageUrl())
                    .placeholder(R.drawable.graduate)
                    .error(R.drawable.graduate)
                    .into(mcircleImgProfile);
        }
    }

    private void initializeEvent() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycleCourses.setHasFixedSize(true);

        // use a linear layout manager
        mRecycleCourses.setLayoutManager(new GridLayoutManager(this, 3));


        String myDataset[]=mStudents.getmCources().trim().split(",");
        // specify an adapter (see also next example)
        CoursesAdapter coursesAdapter = new CoursesAdapter(myDataset);
        mRecycleCourses.setAdapter(coursesAdapter);

    }
    private void setAdapterRecycle() {
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val==1){
                    Toast.makeText(ProfileStudent_ParentsActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();
                }else {
                    String phone = mStudents.getmPhone();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(CommnuicationBetweenActivities.CALL, phone, null));
                    startActivity(intent);
                }
            }
        });
        mBtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val==1){
                    Toast.makeText(ProfileStudent_ParentsActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();
                }else {
                    String number = mStudents.getmPhone();  // The number on which you want to send SMS
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts(CommnuicationBetweenActivities.SMS, number, null)));
                }
            }
        });
        mBtnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val==1){
                    Toast.makeText(ProfileStudent_ParentsActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();
                }else{

                }
            }
        });
        mAddFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val==1){
                    Toast.makeText(ProfileStudent_ParentsActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ProfileStudent_ParentsActivity.this, "Added Successfully in your Widget", Toast.LENGTH_SHORT).show();
                    Paper.init(getApplicationContext());
                    Paper.book().write("NAME_WIDGET",mNameTxt.getText().toString());
                    Paper.book().write("PHONE",mPhoneTxt.getText().toString());
                    Intent intent_meeting_update=new  Intent(ProfileStudent_ParentsActivity.this, MyWidget.class);
                    intent_meeting_update.setAction(MyWidget.UPDATE_MEETING_ACTION);
                    sendBroadcast(intent_meeting_update);
                }
            }
        });
    }
}
