package com.example.muhammad.practice.modal;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.databinding.JobEditorBinding;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class Jobs {
    private String jobId;
    private String jobCompanyId;
    private String jobCompanyName;
    private String jobTitle;
    private String jobDescription;
    private int jobStatus;
    private String statusWithCompanyId;


    public Jobs(){

    }
    public Jobs(String jobCompanyId, String jobTitle, String jobDescription,int jobStatus,String jobCompanyName,String statusWithCompanyId) {
        this.jobCompanyId = jobCompanyId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobStatus = jobStatus;
        this.jobCompanyName = jobCompanyName;
        this.statusWithCompanyId = statusWithCompanyId;

    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(String jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobCompanyName() {
        return jobCompanyName;
    }

    public void setJobCompanyName(String jobCompanyName) {
        this.jobCompanyName = jobCompanyName;
    }

    public String getStatusWithCompanyId() {
        return statusWithCompanyId;
    }

    public void setStatusWithCompanyId(String statusWithCompanyId) {
        this.statusWithCompanyId = statusWithCompanyId;
    }

    public void addNewJob(String jobTitle, String jobDescription, String userId, Context context, FirebaseUtils firebaseUtils){

        String title = jobTitle;
        String desc = jobDescription;
        String companyId = userId;
        firebaseUtils.firebaseJobInsert(companyId,title,desc,context);




    }

    public void fetchSingleJob(String jobId,JobEditorBinding binding,String action,FirebaseUtils firebaseUtils){

        firebaseUtils.firebaseFetchSingleJob(jobId,binding,action);
    }

    public void deleteJob(String jobId,Context context,FirebaseUtils firebaseUtils){
        firebaseUtils.firebaseJobDelete( jobId,context);
    }
    public void updateJob(String jobId,String jobTitle,String jobDescription, Context context,FirebaseUtils firebaseUtils){
        firebaseUtils.firebaseJobUpdate( jobId,jobTitle,jobDescription,context);
    }

    @Override
    public String toString() {
        return "jobID:"+getJobId()+" jobTitle:"+getJobTitle()+" jobDesc:"+getJobDescription()+" jobCompany:"+getJobCompanyName()
                +" companyId:"+getJobCompanyId();
    }
}
