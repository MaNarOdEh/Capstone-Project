package com.example.captonesecondstage.Class;

import android.os.AsyncTask;

import com.example.captonesecondstage.Communication.CommnuicationBetweenActivities;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DoInBackground_AsyncClass extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... strings) {
        FirebaseDatabase.getInstance().getReference().child(CommnuicationBetweenActivities.STUDENT_DB).
                child(FirebaseAuth.getInstance().getUid()).child(strings[0]).setValue(strings[1]);
        return null;
    }
    protected void onProgressUpdate(Integer... progress) {
    }

    protected void onPostExecute(Long result) {
    }

}
