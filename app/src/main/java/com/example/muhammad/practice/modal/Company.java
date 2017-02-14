package com.example.muhammad.practice.modal;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class Company {
    private String mId;
    private String mCompanyName;
    private String mCompanyEmail;
    private String mUserType;

    public Company(String mId,String mCompanyName,String mCompanyEmail,String mUserType){
        this.mId = mId;
        this.mCompanyName = mCompanyName;
        this.mCompanyEmail = mCompanyEmail;
        this.mUserType = mUserType;
    }

    public Company(){

    }
    public String getmUserType() {
        return mUserType;
    }

    public void setmUserType(String mUserType) {
        this.mUserType = mUserType;
    }

    public String getmCompanyName() {
        return mCompanyName;
    }

    public void setmCompanyName(String mCompanyName) {
        this.mCompanyName = mCompanyName;
    }

    public String getmCompanyEmail() {
        return mCompanyEmail;
    }

    public void setmCompanyEmail(String mCompanyEmail) {
        this.mCompanyEmail = mCompanyEmail;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }


}
