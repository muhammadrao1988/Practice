package com.example.muhammad.practice.Firebase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.muhammad.practice.Login;
import com.example.muhammad.practice.MainActivity;
import com.example.muhammad.practice.SessionManager.SessionManager;
import com.example.muhammad.practice.Utils;
import com.example.muhammad.practice.modal.Company;
import com.example.muhammad.practice.modal.Student;
import com.example.muhammad.practice.modal.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class FirebaseUtils {

    public static FirebaseAuth mAuth;
    public static FirebaseAuth.AuthStateListener mAuthListener;
    public static DatabaseReference mDatabase;
    private boolean isLogin = false;
    private Users user;
    private SessionManager sessionManager;
    ProgressDialog progressDialog;

    public FirebaseUtils(){
        FirebaseUtils.mAuth =FirebaseAuth.getInstance();
    }

    //check user log in or not
    public  void checkFireBaseLogin(Context context){
        final Activity activity = (Activity) context;

        FirebaseUtils.mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    Log.i("USERID:","insee"+isLogin);
                    isLogin = true;

                }else{
                    Utils.startIntent(activity,Login.class);

                }
            }
        };

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


                        if (task.isSuccessful()) {
                            final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(progressDialog != null && progressDialog.isShowing())
                                    {
                                        progressDialog.dismiss();
                                    }

                                    Users users = dataSnapshot.getValue(Users.class);

                                    if(users.getmType().equals("student") || users.getmType().equals("company") || users.getmType().equals("admin")){
                                        sessionManager = new SessionManager(activity);
                                        sessionManager.createLoginSession(uid,users.getmType(),users.getmName(),users.getmEmail());

                                        Utils.startIntent(activity,MainActivity.class);
                                    }else{
                                        Toast.makeText(activity,"Invalid user type. Please try again",Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });




                        } else {
                            if(progressDialog != null && progressDialog.isShowing())
                            {
                                progressDialog.dismiss();
                            }

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

    //firebase signup
    public void fireBaseSignUp(String email, String password, final String name, String gender, final Context context, String userType){
        final Activity activity = (Activity) context;
        final String user_name = name;

        final String user_email = email;
        final String student_gender = gender;
        final String type = userType;

        progressDialog = progressDialog.show(activity,"","Please wait..",true);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    if(type.equals("company")) {
                        FirebaseDatabase.getInstance().getReference().
                                child("Users").child(uid).setValue(new Company(uid, user_name, user_email, "company"));
                    }else {
                        FirebaseDatabase.getInstance().getReference().
                                child("Users").child(uid).setValue(new Student(uid, user_name, user_email,student_gender, "student"));
                    }
                    if(progressDialog != null && progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                    sessionManager = new SessionManager(activity);
                    sessionManager.createLoginSession(uid,type,user_name,user_email);
                    Utils.startIntent(activity,MainActivity.class);

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


    //firebase logout
    public static void fireBaseLogOut(){

        mAuth.signOut();

    }




}
