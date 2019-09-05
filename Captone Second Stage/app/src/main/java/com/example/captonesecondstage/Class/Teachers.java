package com.example.captonesecondstage.Class;

import java.util.ArrayList;

public class Teachers {
    String mUserName;
    String mEmail;
    String mPassword;
    String mPhone;
    String mDescritption;
    String mCources;
    ArrayList<String> courStringArrayList;
    String mAdress;
    Double mEvaluation;

    public Teachers() {
    }

    public Teachers(String mUserName, String mEmail, String mPassword,
                    String mPhone, String mDescritption, String mCources,  String mAdress) {
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mPhone = mPhone;
        this.mDescritption = mDescritption;
        this.mCources = mCources;
        this.mAdress = mAdress;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmDescritption() {
        return mDescritption;
    }

    public void setmDescritption(String mDescritption) {
        this.mDescritption = mDescritption;
    }

    public String getmCources() {
        return mCources;
    }

    public void setmCources(String mCources) {
        this.mCources = mCources;
    }

    public ArrayList<String> getCourStringArrayList() {
        return courStringArrayList;
    }

    public void setCourStringArrayList(ArrayList<String> courStringArrayList) {
        this.courStringArrayList = courStringArrayList;
    }

    public String getmAdress() {
        return mAdress;
    }

    public void setmAdress(String mAdress) {
        this.mAdress = mAdress;
    }

    public Double getmEvaluation() {
        return mEvaluation;
    }

    public void setmEvaluation(Double mEvaluation) {
        this.mEvaluation = mEvaluation;
    }
}
