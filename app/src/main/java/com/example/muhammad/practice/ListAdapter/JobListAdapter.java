package com.example.muhammad.practice.ListAdapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.muhammad.practice.R;
import com.example.muhammad.practice.databinding.JobListBinding;
import com.example.muhammad.practice.modal.Jobs;
import com.example.muhammad.practice.modal.Users;

import java.util.ArrayList;

/**
 * Created by Muhammad on 2/23/2017.
 */
public class JobListAdapter extends ArrayAdapter<Jobs> {
    private  ArrayList<Jobs> jobs;
    private JobListBinding binding;
    private Context c;
    public JobListAdapter(Context context, ArrayList<Jobs> jobList) {
        super(context, 0,jobList);
        this.jobs = jobList;
        this.c =context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        // to save memory
        if(view == null) {
//            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = DataBindingUtil.inflate(LayoutInflater.from(c), R.layout.job_list_item, parent,false);
        }else{
            view = binding.getRoot();
        }
        Jobs j = jobs.get(position);


        binding.jobTitle.setText(j.getJobTitle());
        binding.jobCompany.setText(j.getJobCompanyName());



        //return view;
        return binding.getRoot();
    }
}
