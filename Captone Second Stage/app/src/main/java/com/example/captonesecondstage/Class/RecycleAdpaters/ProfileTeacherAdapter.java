package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.Class.Teachers;
import com.example.captonesecondstage.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileTeacherAdapter extends RecyclerView.Adapter<ProfileTeacherAdapter.MyViewHolder> implements Filterable {
    private List<Teachers>mtTeachersList;
    private  List<Teachers>mCopyTeachersList;
    private  OnProfileClicked onProfileClicked;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mtTeachersList = mCopyTeachersList;
                } else {
                    List<Teachers> filteredList = new ArrayList<>();
                    for (Teachers row : mtTeachersList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((row.getmUserName().toLowerCase().contains(charString.toLowerCase()))|| (row.getmPhone().contains(charSequence))) {
                            filteredList.add(row);
                        }
                    }

                    mtTeachersList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mtTeachersList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mtTeachersList = (ArrayList<Teachers>) filterResults.values;
                // refresh the list with filtered data
                notifyDataSetChanged();
            }
        };
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

    public ProfileTeacherAdapter(ArrayList<Teachers> teachers, OnProfileClicked onProfileClicked) {
        this.mtTeachersList=teachers;
        this.mCopyTeachersList=teachers;
        this.onProfileClicked=onProfileClicked;
    }
    public interface  OnProfileClicked{
        public void onProfileClicked(int position);
    }
    public List<Teachers>getList(){
        return mtTeachersList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProfileTeacherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_teacher_short_info, parent, false);
        return new ProfileTeacherAdapter.MyViewHolder(v,onProfileClicked);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ProfileTeacherAdapter.MyViewHolder holder, int position) {
        Teachers teachers=mtTeachersList.get(position);
        holder.mUserNameTv.setText(teachers.getmUserName());
        holder.mUserPhoneTv.setText(teachers.getmPhone());
        holder.mCoursesTv.setText(teachers.getmCources()!=null?teachers.getmCources().trim():"");
        Picasso.get().load(teachers.getmImageUrl())
                .placeholder(R.drawable.teachers)
                .error(R.drawable.teachers)
                .into(holder.mImageProfileCircle);

    }

    @Override
    public int getItemCount() {
        return mtTeachersList.size();
    }
}

