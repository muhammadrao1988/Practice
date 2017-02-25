package com.example.muhammad.practice.modal;

/**
 * Created by Muhammad on 2/18/2017.
 */
public class Users {


    private String mId;
    private String mType;
    private String mEmail;
    private String mName;
    public Users(){}

    public Users(String mId, String mType, String mEmail, String mName) {
        this.mId = mId;
        this.mType = mType;
        this.mEmail = mEmail;
        this.mName = mName;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    @Override
    public String toString() {
        return "userId:"+getmId()+" email:"+getmEmail()+" name:"+getmName()+" type:"+getmType();

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
