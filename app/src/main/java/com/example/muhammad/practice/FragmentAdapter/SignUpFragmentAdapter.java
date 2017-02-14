package com.example.muhammad.practice.FragmentAdapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.muhammad.practice.CompanySignUpFragment;
import com.example.muhammad.practice.R;
import com.example.muhammad.practice.StudentSignUpFragment;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class SignUpFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public SignUpFragmentAdapter(Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
        return new StudentSignUpFragment();
        }else {
            return new CompanySignUpFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return mContext.getString(R.string.sign_up_student);
        }else{
            return mContext.getString(R.string.sign_up_company);
        }
    }
}
