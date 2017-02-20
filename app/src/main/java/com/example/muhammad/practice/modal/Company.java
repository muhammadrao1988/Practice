package com.example.muhammad.practice.modal;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class Company {
    private String mId;
    private String mName;
    private String mEmail;
    private String mType;

    public Company(String mId, String mName, String mEmail, String mType){
        this.mId = mId;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mType = mType;
    }

    public Company(){

    }
    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
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

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }


}
