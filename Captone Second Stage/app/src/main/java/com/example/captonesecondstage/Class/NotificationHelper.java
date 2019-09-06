package com.example.captonesecondstage.Class;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.captonesecondstage.R;
import com.example.captonesecondstage.ui.Activity.HomePageActivity;

public class NotificationHelper {
    private static final String CHANEEL_ID="PRIVATE_TEACHER";

    public  static void display_Notification(Context context, String title, String body){
        Intent intent=new Intent(context, HomePageActivity.class);
        intent.putExtra("notifications","notification_testings");

        PendingIntent pendingIntent= PendingIntent.getActivity(context,100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(context,CHANEEL_ID)
                .setSmallIcon(R.drawable.graduate)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(1,mBuilder.build());
    }


}
