package com.example.captonesecondstage.Class;

public class Notification {
    String mText;
    String mUserName;
    String mTime;

    public Notification() {
    }

    public Notification(String mText, String mUserName, String mTime) {
        this.mText = mText;
        this.mUserName = mUserName;
        this.mTime = mTime;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }
}
