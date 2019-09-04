package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {
    private List<Teachers>mtTeachersList;
    private  OnProfileClicked onProfileClicked;
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
        @BindView(R.id.img_star_first)@Nullable()
        ImageView mImgStartFirst;
        @BindView(R.id.img_star_second)@Nullable()
        ImageView mImgStarSecond;
        @BindView(R.id.img_star_third)@Nullable()
        ImageView  mImgStarThird;
        @BindView(R.id.img_star_fourth)@Nullable()
        ImageView  mImgStarFourth;
        @BindView(R.id.img_star_fifth)@Nullable()
        ImageView mImgStarFifth;
        @BindView(R.id.btn_show_profile)@Nullable()
        Button mBtnShowProfile;
        OnProfileClicked onProfileClicked;


        public MyViewHolder(View viewItems,OnProfileClicked onProfileClicked) {
            super(viewItems);
            ButterKnife.bind(this, viewItems);
            this.onProfileClicked=onProfileClicked;
            mBtnShowProfile.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onProfileClicked.onProfileClicked(getAdapterPosition());
        }
    }
    public ProfileAdapter(ArrayList<Teachers> teachers,OnProfileClicked onProfileClicked) {
        this.mtTeachersList=teachers;
        this.onProfileClicked=onProfileClicked;
    }
    public interface  OnProfileClicked{
        public void onProfileClicked(int position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_teacher_short_info, parent, false);
        return new ProfileAdapter.MyViewHolder(v,onProfileClicked);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProfileAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mtTeachersList.size();
    }

}

