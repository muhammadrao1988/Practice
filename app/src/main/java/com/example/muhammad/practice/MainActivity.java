package com.example.muhammad.practice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.databinding.MainActivityBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUtils firebaseUtils = new FirebaseUtils();

        if(firebaseUtils.checkFireBaseLogin()){
            Log.i("Main","UserExists");
        }else{
            Intent intent = new Intent(MainActivity.this,Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


    }


}
