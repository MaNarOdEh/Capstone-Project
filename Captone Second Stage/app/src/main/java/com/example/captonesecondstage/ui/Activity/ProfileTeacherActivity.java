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
import com.example.captonesecondstage.R;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_teacher);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        //initialize Events
        initializeEvent();
        //setAdapters
        setAdapterRecycle();
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
                String phone = "0597853325";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        mBtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = "0597853325";  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));

            }
        });
    }
}
