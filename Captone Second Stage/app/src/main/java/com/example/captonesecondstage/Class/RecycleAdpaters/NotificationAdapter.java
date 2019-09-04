package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.captonesecondstage.Class.Notification;
import com.example.captonesecondstage.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder>{

    List<Notification>mNotificationList;

    OnNotificationClicked onNotificationClicked;

    OnDeleteClicked onDeleteClicked;



    public NotificationAdapter(ArrayList<Notification>mNotificationList,OnNotificationClicked onNotificationClicked,OnDeleteClicked onDeleteClicked){
        this.mNotificationList=mNotificationList;
        this.onDeleteClicked=onDeleteClicked;
        this.onNotificationClicked=onNotificationClicked;
    }

    public NotificationAdapter(ArrayList<Notification>mNotificationList,OnNotificationClicked onNotificationClicked){
        this.mNotificationList=mNotificationList;
        this.onDeleteClicked=onDeleteClicked;
        this.onNotificationClicked=onNotificationClicked;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // each data item is just a string in this case
        @BindView(R.id.img_delete_click)@Nullable()
        ImageView mImgDeleteClick;
        @BindView(R.id.card_view_notification)@Nullable()
        CardView mCardViewnotification;
        @BindView(R.id.txt_notification_status)@Nullable()
        TextView mTxtnotificationStatus;
        @BindView(R.id.circleImg_profile)@Nullable()
        CircleImageView mCircleNotificationStatus;

        OnNotificationClicked onNotificationClicked;

        OnDeleteClicked onDeleteClicked;


        public MyViewHolder(View viewItems, OnNotificationClicked onProfileClicked,OnDeleteClicked onDeleteClicked) {
            super(viewItems);
            ButterKnife.bind(this, viewItems);
            this.onNotificationClicked=onProfileClicked;
            this.onDeleteClicked=onDeleteClicked;
            mImgDeleteClick.setOnClickListener(this);
            mCardViewnotification.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDeleteClicked.onDeleteClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onNotificationClicked.onNotificationClicked(getAdapterPosition());
            return false;
        }
    }
    public interface  OnNotificationClicked{
        public void onNotificationClicked(int position);

    }
    public  interface  OnDeleteClicked{
        public void onDeleteClicked(int position);
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_notification, parent, false);
        return new NotificationAdapter.MyViewHolder(v,onNotificationClicked,onDeleteClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }
}
