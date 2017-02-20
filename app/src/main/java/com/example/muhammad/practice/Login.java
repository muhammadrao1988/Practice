package com.example.muhammad.practice;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.databinding.LoginActivityBinding;


public class Login extends AppCompatActivity {

    private LoginActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.signUpCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startIntent(Login.this,SignUp.class);

            }
        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtils firebaseUtils = new FirebaseUtils();

                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if(Utils.checkStringEmpty(email) && Utils.checkStringEmpty(password) ) {
                    firebaseUtils.fireBaseLogin(email, password,  Login.this);
                }else{
                    Toast.makeText(Login.this,"Please fill out all fields",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
