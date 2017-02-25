package com.example.muhammad.practice;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.FragmentAdapter.MainFragmentAdapter;
import com.example.muhammad.practice.SessionManager.SessionManager;
import com.example.muhammad.practice.databinding.JobEditorBinding;
import com.example.muhammad.practice.modal.Jobs;
import com.example.muhammad.practice.modal.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobEditorActivity extends AppCompatActivity {
    private JobEditorBinding binding;
    private FirebaseUtils firebaseUtils;
    private SessionManager session;
    private String userId;
    private String userType;
    private int previousTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(JobEditorActivity.this,R.layout.activity_job_editor);
        //initializing  session manager
        session = new SessionManager(JobEditorActivity.this);


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        userId = session.getUserDetails().get(session.KEY_ID);
        userType = session.getUserDetails().get(session.KEY_TYPE);
        if(!userId.equals("")) {

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

            firebaseUtils = new FirebaseUtils();
            final boolean job_id_exists = getIntent().hasExtra("job_id");
            final Jobs job = new Jobs();



            //fetch job and set ui if job id exists
            if(job_id_exists) {
                final String job_id = getIntent().getStringExtra("job_id");
                Log.i("JOBID:",job.toString());

                job.fetchSingleJob(job_id,binding,(userType.equals("company") ? "edit" :"view"),firebaseUtils);

                //delete job (admin and company can delete job)
                binding.deleteJobBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(JobEditorActivity.this)
                                .setTitle("Delete")
                                .setMessage("Do you really want to delete this job?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        job.deleteJob(job_id,JobEditorActivity.this,firebaseUtils);
                                        Toast.makeText(JobEditorActivity.this, "Job deleted successfully", Toast.LENGTH_SHORT).show();
                                       goBack();
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();
                    }
                });
                //update job
                binding.jobUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String jobTitle = binding.jobTitle.getText().toString();
                        String jobDesc = binding.jobDescription.getText().toString();
                        if (Utils.checkStringEmpty(jobTitle) || Utils.checkStringEmpty(jobDesc)) {
                            job.updateJob(job_id,jobTitle, jobDesc,  JobEditorActivity.this, firebaseUtils);
                            Toast.makeText(JobEditorActivity.this, "Job updated successfully", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(JobEditorActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //apply job
                binding.applyJob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       firebaseUtils.mDatabase =  FirebaseDatabase.getInstance().getReference();
                        firebaseUtils.mDatabase.child("Users")
                                .child(binding.companId.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        Users companyUser = dataSnapshot.getValue(Users.class);
                                        Log.i("USERINFO:", companyUser.toString()+"---"+binding.companId.getText().toString());
                                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{companyUser.getmEmail()});
                                        intent.putExtra(Intent.EXTRA_SUBJECT, binding.jobTitleView.getText());
                                        intent.putExtra(Intent.EXTRA_TEXT, "");
                                        if (intent.resolveActivity(getPackageManager()) != null) {
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(JobEditorActivity.this, "Unable to start email application", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                    }
                });
            }
            if(userType.equals("company"))
            {
                previousTab = 1;
                binding.viewJobLayout.setVisibility(View.INVISIBLE);

                if(job_id_exists){
                    binding.jobMainHeading.setText("Edit Job");
                    binding.jobSubmit.setVisibility(View.INVISIBLE);
                }else{
                    binding.jobMainHeading.setText("Add New Job");
                    binding.jobUpdate.setVisibility(View.INVISIBLE);
                    binding.deleteJobBtn.setVisibility(View.INVISIBLE);
                }

                //add new job
                binding.jobSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String jobTitle = binding.jobTitle.getText().toString();
                        String jobDesc = binding.jobDescription.getText().toString();
                        if (Utils.checkStringEmpty(jobTitle) || Utils.checkStringEmpty(jobDesc)) {
                            new Jobs().addNewJob(jobTitle, jobDesc, userId, JobEditorActivity.this, firebaseUtils);
                            Toast.makeText(JobEditorActivity.this, "Job added successfully", Toast.LENGTH_SHORT).show();
                            goBack();
                        }else{
                            Toast.makeText(JobEditorActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else
            {
                binding.addEditJobLayout.setVisibility(View.INVISIBLE);
                previousTab = 2;
                if(userType.equals("student")){
                    previousTab =1;
                    binding.deleteJobBtn.setVisibility(View.INVISIBLE);
                }
            }
        }else{
            firebaseUtils.fireBaseLogOut();
            session.logoutUser();
        }

    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent objEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyUp(keyCode, objEvent);
    }
    void goBack(){
        Toast.makeText(JobEditorActivity.this,"TabJob:"+previousTab,Toast.LENGTH_SHORT).show();
        Intent r = new Intent(JobEditorActivity.this,MainActivity.class);
        r.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        r.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
       // r.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        r.putExtra("selected_tab", previousTab);


        startActivity(r);

    }
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        goBack();

        // finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.logout){

            FirebaseUtils.fireBaseLogOut();
            session.logoutUser();
        }else if(id==android.R.id.home){ //back button click
            goBack();
        }
        return super.onOptionsItemSelected(item);
    }
}
