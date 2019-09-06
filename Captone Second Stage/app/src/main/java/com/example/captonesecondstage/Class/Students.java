package com.example.captonesecondstage.Class;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Students implements Parcelable {
    String mUserName;
    String mEmail;
    String mPassword;
    String mPhone;
    String mDescritption;
    String mCources;
    ArrayList<String> courStringArrayList;
    String mAdress;
    public  Students(){

    }
    public Students(String mUserName, String mEmail,
                    String mPassword, String mPhone,
                    String mDescritption, String mCources, String mAdress) {
        this.mUserName = mUserName;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mPhone = mPhone;
        this.mDescritption = mDescritption;
        this.mCources = mCources;
        this.mAdress = mAdress;
    }

    protected Students(Parcel in) {
        mUserName = in.readString();
        mEmail = in.readString();
        mPassword = in.readString();
        mPhone = in.readString();
        mDescritption = in.readString();
        mCources = in.readString();
        courStringArrayList = in.createStringArrayList();
        mAdress = in.readString();
    }

    public static final Creator<Students> CREATOR = new Creator<Students>() {
        @Override
        public Students createFromParcel(Parcel in) {
            return new Students(in);
        }

        @Override
        public Students[] newArray(int size) {
            return new Students[size];
        }
    };

    public String getmUserName() {
        return mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmDescritption() {
        return mDescritption;
    }

    public String getmCources() {
        return mCources;
    }

    public ArrayList<String> getCourStringArrayList() {
        return courStringArrayList;
    }

    public String getmAdress() {
        return mAdress;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public void setmDescritption(String mDescritption) {
        this.mDescritption = mDescritption;
    }

    public void setmCources(String mCources) {
        this.mCources = mCources;
    }

    public void setmAdress(String mAdress) {
        this.mAdress = mAdress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUserName);
        parcel.writeString(mEmail);
        parcel.writeString(mPassword);
        parcel.writeString(mPhone);
        parcel.writeString(mDescritption);
        parcel.writeString(mCources);
        parcel.writeStringList(courStringArrayList);
        parcel.writeString(mAdress);
    }
}
