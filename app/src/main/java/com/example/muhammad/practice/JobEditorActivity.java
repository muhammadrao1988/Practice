package com.example.muhammad.practice;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.muhammad.practice.FragmentAdapter.MainFragmentAdapter;
import com.example.muhammad.practice.databinding.JobEditorBinding;

public class JobEditorActivity extends AppCompatActivity {
    private JobEditorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(JobEditorActivity.this,R.layout.activity_job_editor);

    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
       // Utils.startIntent(JobEditorActivity.this,MainActivity.class);
       
        finish();
    }
}
