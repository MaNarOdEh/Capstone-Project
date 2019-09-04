package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileStudentAdapter  extends RecyclerView.Adapter<ProfileStudentAdapter.MyViewHolder>  {
    private List<Students>mStudentList;
    OnProfileStudentClicked onProfileStudentClicked;

    public  ProfileStudentAdapter(ArrayList<Students>mStudentList,OnProfileStudentClicked onProfileStudentClicked){
        this.mStudentList=mStudentList;
        this.onProfileStudentClicked=onProfileStudentClicked;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        @BindView(R.id.image_profile_circle)@Nullable()
        CircleImageView mImageProfileCircle;
        @BindView(R.id.user_name_tv)@Nullable()
        TextView mUserNameTv;
        @BindView(R.id.user_phone_tv)@Nullable()
        TextView mUserPhoneTv;
        @BindView(R.id.courses_tv)@Nullable()
        TextView mCoursesTv;
        @BindView(R.id.btn_show_profile)@Nullable()
        Button mBtnShowProfile;
        OnProfileStudentClicked onProfileClicked;

        public MyViewHolder(View viewItems, OnProfileStudentClicked onProfileClicked) {
            super(viewItems);
            ButterKnife.bind(this, viewItems);
            this.onProfileClicked=onProfileClicked;
            mBtnShowProfile.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProfileClicked.onProfileStudentClicked(getAdapterPosition());
        }
    }
    public interface  OnProfileStudentClicked{
        public void onProfileStudentClicked(int position);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_student_short_info, parent, false);
        return new MyViewHolder(v,onProfileStudentClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileStudentAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }
}
