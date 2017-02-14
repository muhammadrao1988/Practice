package com.example.muhammad.practice;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.muhammad.practice.FragmentAdapter.SignUpFragmentAdapter;
import com.example.muhammad.practice.databinding.SignUpBinding;

public class SignUp extends AppCompatActivity {

    private SignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        SignUpFragmentAdapter fa = new SignUpFragmentAdapter(this,getSupportFragmentManager());
        binding.signUpPager.setAdapter(fa);
        binding.signUpTab.setupWithViewPager(binding.signUpPager);
    }
}
