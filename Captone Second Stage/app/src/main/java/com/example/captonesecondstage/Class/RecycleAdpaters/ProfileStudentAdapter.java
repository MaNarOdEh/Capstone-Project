package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.captonesecondstage.Class.Students;
import com.example.captonesecondstage.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileStudentAdapter  extends RecyclerView.Adapter<ProfileStudentAdapter.MyViewHolder> implements Filterable {
    private List<Students>mStudentList; //adapters..
    public List<Students>mCopyStudentList; //copy of my adapters..
    OnProfileStudentClicked onProfileStudentClicked;

    public  ProfileStudentAdapter(ArrayList<Students>mStudentList,OnProfileStudentClicked onProfileStudentClicked){
        this.mStudentList=mStudentList;
        this.mCopyStudentList=mStudentList;
        this.onProfileStudentClicked=onProfileStudentClicked;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mStudentList = mCopyStudentList;
                } else {
                    List<Students> filteredList = new ArrayList<>();
                    for (Students row : mStudentList) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if ((row.getmUserName().toLowerCase().contains(charString.toLowerCase()))|| (row.getmPhone().contains(charSequence))) {
                            filteredList.add(row);
                        }
                    }

                    mStudentList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mStudentList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mStudentList = (ArrayList<Students>) filterResults.values;
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
            //mC.indexOf(arraylist1.get(getAdapterPosition()));
            onProfileClicked.onProfileStudentClicked(getLayoutPosition());
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
        Students students=mStudentList.get(position);
        holder.mCoursesTv.setText(students.getmCources());
        holder.mUserNameTv.setText(students.getmUserName());
        holder.mUserPhoneTv.setText(students.getmPhone());
        Picasso.get().load(students.getmImageUrl())
                .placeholder(R.drawable.graduate)
                .error(R.drawable.graduate)
                .into(holder.mImageProfileCircle);

    }
    public List<Students>getList(){
        return mStudentList;
    }


    @Override
    public int getItemCount() {
        if(mStudentList==null)
            return 0;
        return mStudentList.size();
    }

}
