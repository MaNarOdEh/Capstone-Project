package com.example.captonesecondstage.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.captonesecondstage.Class.RecycleAdpaters.CoursesAdapter;
import com.example.captonesecondstage.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileTeacherActivity extends AppCompatActivity {

    @BindView(R.id.add_fav_img)
    ImageView mAddFavImg;
    @BindView(R.id.star_img_f)
    ImageView mStarImageF;
    @BindView(R.id.star_img_s)
    ImageView mStartImageS;
    @BindView(R.id.star_img_t)
    ImageView mStartImageT;
    @BindView(R.id.star_img_fourth)
    ImageView mStartImageFour;
    @BindView(R.id.star_img_fifth)
    ImageView mStartImageFifith;
    @BindView(R.id.circleImg_profile)
    CircleImageView mcircleImgProfile;
    @BindView(R.id.name_txt)
    TextView mNameTxt;
    @BindView(R.id.phone_txt)
    TextView mPhoneTxt;
    @BindView(R.id.aboutUs_txt)
    TextView mAboutUsTxt;
    @BindView(R.id.btn_call)
    Button mBtnCall;
    @BindView(R.id.btn_sms)
    Button mBtnSMS;
    @BindView(R.id.recycle_courses)
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

            }
        });
        mBtnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
