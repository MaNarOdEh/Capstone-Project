package com.example.captonesecondstage.ui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.captonesecondstage.Class.Evaluation;
import com.example.captonesecondstage.Class.Notification;
import com.example.captonesecondstage.Class.RecycleAdpaters.CoursesAdapter;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.example.captonesecondstage.MyWidget;
import com.example.captonesecondstage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

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
    @BindView(R.id.btn_evaluate)@Nullable
    Button mBtnEvaluate;
    @BindView(R.id.recycle_courses)@Nullable()
    RecyclerView mRecycleCourses;
    @BindView(R.id.evaluation_txt)@Nullable TextView mEvalutionTxt;
    Teachers mTeachers;
    Dialog dialog;
    String total;
    int val;
    Evaluation evaluation;
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
        if(val==0){
            SimpleDateFormat formatter= new SimpleDateFormat("HH:mm");
            Date date = new Date(System.currentTimeMillis());
            //System.out.println(formatter.format(date));
            Notification notification=new Notification(HomePageActivity.userName+" visit your profile",mTeachers.getmUserName(),formatter.format(date)+" ");
            FirebaseDatabase.getInstance().getReference().child(CommnuicationBetweenActivities.NOTIFICATIONST)
                    .child(mTeachers.getmUserName()).push().setValue(notification);
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
        getTheAverageEvalution();
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
        mAddFavImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val==1) {
                    Toast.makeText(ProfileTeacherActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ProfileTeacherActivity.this, "Added Successfully in your Widget", Toast.LENGTH_SHORT).show();
                    Paper.init(getApplicationContext());
                    Paper.book().write("NAME_WIDGET",mNameTxt.getText().toString());
                    Paper.book().write("PHONE",mPhoneTxt.getText().toString());
                    Intent intent_meeting_update=new  Intent(ProfileTeacherActivity.this, MyWidget.class);
                    intent_meeting_update.setAction(MyWidget.UPDATE_MEETING_ACTION);
                    sendBroadcast(intent_meeting_update);
                }
            }
        });
        mBtnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(val==1){
                    Toast.makeText(ProfileTeacherActivity.this, "Can't do that you are in Watch Mode!!", Toast.LENGTH_LONG).show();
                }else{
                    checkCanYouEvaluate();
                }
            }
        });
        dialog  = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_evaluation);

        dialog.findViewById(R.id.btn_evaluate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  checkPremissionTakePhotoAndCaptureImage();
                EditText editText=dialog.findViewById(R.id.input_dialog_et);
                String evaluate=editText.getText().toString().trim();
                if(evaluate.equals("1")||evaluate.equals("2")||evaluate.equals("3")||evaluate.equals("4")||evaluate.equals("5")){
                    dialog.dismiss();
                    setEvaluate(Integer.parseInt(evaluate));
                }else{
                    editText.setError("Input Number Between 1 and 5 Please!!");
                }


            }
        });
    }
    private  void setEvaluate(int evl){
        //increase the number  // adding the totals
        //get the prevouis evalution  and editing it
        evaluation.increase(evl);
        FirebaseDatabase.getInstance().getReference()
                .child(CommnuicationBetweenActivities.RESULTEVALUATION).child(mTeachers.getmUserName())
        .setValue(evaluation);
        setNewValues();
        FirebaseDatabase.getInstance().getReference()
                .child(CommnuicationBetweenActivities.STUDENTTEACH).child((HomePageActivity.userName)).child(mTeachers.getmUserName()).setValue("0");
    }
    private void checkCanYouEvaluate(){
        FirebaseDatabase.getInstance().getReference()
                .child(CommnuicationBetweenActivities.STUDENTTEACH).child((HomePageActivity.userName)).child(mTeachers.getmUserName()).
                addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null&&dataSnapshot.getValue()!=null&&dataSnapshot.getValue().equals("1")){
                    dialog.show();

                }else if(dataSnapshot!=null&&dataSnapshot.getValue()!=null&&dataSnapshot.getValue().equals("0")){
                    Toast.makeText(ProfileTeacherActivity.this, "You Already  Evaluate That Teacher!! ",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ProfileTeacherActivity.this, "You cant Evaluate That Teacher!! the teacher does not adding you",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getTheAverageEvalution(){
        //numbers //totals // divide them
        FirebaseDatabase.getInstance().getReference()
                .child(CommnuicationBetweenActivities.RESULTEVALUATION).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null&&dataSnapshot.child(mTeachers.getmUserName()).exists()){
                     evaluation=dataSnapshot.child(mTeachers.getmUserName()).getValue(Evaluation.class);
                    Toast.makeText(ProfileTeacherActivity.this, evaluation.getNumber()+"   "+evaluation.getTotal(), Toast.LENGTH_SHORT).show();
                   total=""+Math.round(Double.parseDouble(evaluation.getTotal())/Double.parseDouble(evaluation.getNumber()));
                    mEvalutionTxt.setText(total+"/5");
                   setImages(Math.round(Double.parseDouble(evaluation.getTotal())/Double.parseDouble(evaluation.getNumber())));
                }else{
                    total="5";
                    mEvalutionTxt.setText(total+"/5");
                    evaluation=new Evaluation("5","1");
                    setImages((long) 5);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void setNewValues(){
        Long d=Math.round(Double.parseDouble(evaluation.getTotal())/Double.parseDouble(evaluation.getNumber()));
        mEvalutionTxt.setText(d+"/5");
        setImages(d);
    }
    public void setImages(Long number){
        Toast.makeText(this, number+"   ", Toast.LENGTH_SHORT).show();
        if(number>=5){

        }else if(number>=4){
       //     mStartImageFifith.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
        }else if(number>=3){
          //  mStartImageFour.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
           // mStartImageFifith.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
        }else if(number>=2){

            //mStartImageT.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
            //mStartImageFour.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
            //mStartImageFifith.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
        }else{
            //mStartImageS.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
            //mStartImageT.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
            //mStartImageFour.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
            //mStartImageFifith.setBackgroundTintList(ContextCompat.getColorStateList(ProfileTeacherActivity.this, R.color.colorWhite));
        }

    }
}
