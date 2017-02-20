package com.example.muhammad.practice.FragmentAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.muhammad.practice.CompanyFragment;
import com.example.muhammad.practice.JobsFragment;
import com.example.muhammad.practice.R;
import com.example.muhammad.practice.StudentFragment;

/**
 * Created by Muhammad on 2/18/2017.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalFragment;
    private String userType;
    private String userId;


    public MainFragmentAdapter(FragmentManager fm,Context context,int totalFragment,String userType,String userId) {
        super(fm);
        this.context = context;
        this.totalFragment = totalFragment;
        this.userType = userType;
        this.userId = userId;
    }

    @Override
    public Fragment getItem(int position) {
        if(this.userType.equals("admin")){
            if(position==0){
                return new StudentFragment();
            }else if(position==1){
                return new CompanyFragment();
            }else{
                return new JobsFragment();
            }

        }else if(this.userType.equals("company")){
            if(position==0){
                return new StudentFragment();
            }else{
                return new JobsFragment();
            }
        }else if(this.userType.equals("student")){
            if(position==0){
                return new CompanyFragment();
            }else{
                return new JobsFragment();
            }
        }else {

            return null;
        }
    }

    @Override
    public int getCount() {
        return this.totalFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(this.userType.equals("admin")){
            if(position==0){
                return context.getString(R.string.student);

            }else if(position==1){
                return context.getString(R.string.company);
            }else{
                return context.getString(R.string.job);
            }

        }else if(this.userType.equals("company")){
            if(position==0){
                return context.getString(R.string.student);
            }else{
                return context.getString(R.string.job);
            }
        }else {
            if(position==0){
                return context.getString(R.string.company);
            }else{
                return context.getString(R.string.job);
            }
        }


    }
}
