package com.example.muhammad.practice.Firebase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.muhammad.practice.Login;
import com.example.muhammad.practice.MainActivity;
import com.example.muhammad.practice.modal.Company;
import com.example.muhammad.practice.modal.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class FirebaseUtils {

    public static FirebaseAuth mAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    private boolean isLogin = false;
    ProgressDialog progressDialog;

    public FirebaseUtils(){
        FirebaseUtils.mAuth =FirebaseAuth.getInstance();
    }

    //check user log in or not
    public  boolean checkFireBaseLogin(){


        FirebaseUtils.mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    isLogin = true;
                }
            }
        };
        return isLogin;
    }

    //will use in activity start function
    public static void addAuthOnStart(){
        mAuth.addAuthStateListener(mAuthListener);
    }
    //will use in activity onstop function
    public static void removeAuthOnStop(){
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    //Firebase login
    public  void fireBaseLogin(String email, String password, final Context context){
        final Activity activity = (Activity) context;
        progressDialog = progressDialog.show(activity,"","Please wait..",true);

        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(progressDialog != null && progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        } else {
                            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                            builder.setMessage(task.getException().getMessage())
                                    .setTitle("Error in authentication")
                                    .setPositiveButton(android.R.string.ok, null);
                            android.support.v7.app.AlertDialog dialog = builder.create();
                            dialog.show();
                        }


                    }
                });
    }

    public void fireBaseSignUp(String email, String password, String companyName, String studentName, String gender,final Context context, final String userType){
        final Activity activity = (Activity) context;
        final String company_name = companyName;
        final String student_name = studentName;
        final String user_email = email;
        final String student_gender = gender;

        progressDialog = progressDialog.show(activity,"","Please wait..",true);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    if(userType.equals("company")) {
                        FirebaseDatabase.getInstance().getReference().
                                child("Users").child(uid).setValue(new Company(uid, company_name, user_email, "company"));
                    }else {
                        FirebaseDatabase.getInstance().getReference().
                                child("Users").child(uid).setValue(new Student(uid, student_name, user_email,student_gender, "student"));
                    }
                    if(progressDialog != null && progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                } else {
                    if(progressDialog != null && progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
                    builder.setMessage(task.getException().getMessage())
                            .setTitle("Error in Sing UP")
                            .setPositiveButton(android.R.string.ok, null);
                    android.support.v7.app.AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }



    public static void fireBaseLogOut(){
        mAuth.signOut();
    }

}
