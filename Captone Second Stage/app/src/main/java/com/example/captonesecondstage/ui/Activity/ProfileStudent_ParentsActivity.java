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

import com.example.captonesecondstage.Class.RecycleAdpaters.CoursesAdapter;
import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_student__parents);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        Intent intent=getIntent();
        if(intent!=null){
            mStudents=intent.getParcelableExtra("STUDENTS");
        }else{
            finish();
        }
        //show Student Info
        showInfo();
        //initialize Events
        initializeEvent();
        //setAdapters
        setAdapterRecycle();

    }
    private  void showInfo(){
        mNameTxt.setText(mStudents.getmUserName());
        mPhoneTxt.setText(mStudents.getmPhone());
        mAboutUsTxt.setText(mStudents.getmDescritption()+"\n"+
                mStudents.getmAdress());
        Picasso.get().load(mStudents.getmImageUrl())
                .placeholder(R.drawable.graduate)
                .error(R.drawable.graduate)
                .into(mcircleImgProfile);
    }

    private void initializeEvent() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycleCourses.setHasFixedSize(true);

        // use a linear layout manager
        mRecycleCourses.setLayoutManager(new GridLayoutManager(this, 3));


        String myDataset[]={"JAVA","PHP","CSS","PYTHON","JAVAEE","HTML","ENGLISH"};
        // specify an adapter (see also next example)
        CoursesAdapter coursesAdapter = new CoursesAdapter(myDataset);
        mRecycleCourses.setAdapter(coursesAdapter);

    }
    private void setAdapterRecycle() {
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = mStudents.getmPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        mBtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mStudents.getmPhone();  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
            }
        });
        mBtnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
