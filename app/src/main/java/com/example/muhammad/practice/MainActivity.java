package com.example.muhammad.practice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.FragmentAdapter.MainFragmentAdapter;
import com.example.muhammad.practice.SessionManager.SessionManager;
import com.example.muhammad.practice.databinding.MainActivityBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private MainActivityBinding binding;
    private SessionManager session;
    private String mUserType;
    private String mId;
    private FirebaseUtils firebaseUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);


        //initializing firebase utility
        firebaseUtils = new FirebaseUtils();

        //initializing  session manager
        session = new SessionManager(MainActivity.this);


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        HashMap<String, String> userData = session.getUserDetails();

        // id and type
        this.mId = userData.get(SessionManager.KEY_ID);
        this.mUserType = userData.get(SessionManager.KEY_TYPE);

        int totalTab = (this.mUserType.equals("admin") ? 3 : 2);


        if(session.isLoggedIn() && Utils.validUsertype(this.mUserType)) {
            // get user data from session

            MainFragmentAdapter fa = new MainFragmentAdapter(getSupportFragmentManager(), MainActivity.this, totalTab, this.mUserType, this.mId);
            binding.MainActivityPager.setAdapter(fa);
            binding.MainActivityTab.setupWithViewPager(binding.MainActivityPager);



        }else{
            FirebaseUtils.fireBaseLogOut();
            session.logoutUser();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        //FirebaseUtils.addAuthOnStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //FirebaseUtils.removeAuthOnStop();
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
        }
        return super.onOptionsItemSelected(item);
    }
}
