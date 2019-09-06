package com.example.captonesecondstage.Class;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Teachers implements Parcelable {
    String mUserName;
    String mEmail;
    String mPassword;
    String mPhone;
    String mDescritption;
    String mCources;
    ArrayList<String> courStringArrayList;
    String mAdress;
    Double mEvaluation;
    String mImageUrl;

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

    public static final Creator<Teachers> CREATOR = new Creator<Teachers>() {
        @Override
        public Teachers createFromParcel(Parcel in) {
            return new Teachers(in);
        }

        @Override
        public Teachers[] newArray(int size) {
            return new Teachers[size];
        }
    };

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

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public ArrayList<String> getCourStringArrayList()
    {
        if(courStringArrayList==null||courStringArrayList.size()==0){
           String arr[]=mCources.split(",");
           for(int i=0;i<arr.length;i++){
               courStringArrayList.add(arr[i]);
           }
        }
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

    public Teachers(Parcel parcel){

        mUserName = parcel.readString();
        mEmail = parcel.readString();
        mPassword = parcel.readString();
        mPhone = parcel.readString();
        mDescritption = parcel.readString();
        mCources = parcel.readString();
        courStringArrayList = parcel.createStringArrayList();
        mAdress = parcel.readString();
        if (parcel.readByte() == 0) {
            mEvaluation = null;
        } else {
            mEvaluation = parcel.readDouble();
        }
        mImageUrl=parcel.readString();
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
        if (mEvaluation == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(mEvaluation);
        }
        parcel.writeString(mImageUrl);
    }
}
