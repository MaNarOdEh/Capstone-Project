package com.example.captonesecondstage.Class;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String token="Final token";
    String title="",body="",id="";
    private String channel_id="";

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData().size()>0){

        }
        if(remoteMessage.getNotification()!=null){
            String title=remoteMessage.getNotification().getTitle();
            String msg_content=remoteMessage.getNotification().getBody();
            NotificationHelper.display_Notification(getApplicationContext(),title,msg_content);
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String recent_tokden=s;
        Log.d(token,recent_tokden);
    }
}
