package com.example.muhammad.practice.modal;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class Student {
    private String mId;
    private String mName;
    private String mEmail;
    private String mGender;



    private String mType;

    public Student(String mId, String mName, String mEmail, String mGender,String mType ) {
        this.mId = mId;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mGender = mGender;
        this.mType = mType;
    }

    public Student(){

    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmType() {return mType;}

    public void setmType(String mType) {this.mType = mType;}
}
