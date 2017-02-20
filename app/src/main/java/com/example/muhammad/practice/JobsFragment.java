package com.example.muhammad.practice;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammad.practice.SessionManager.SessionManager;
import com.example.muhammad.practice.databinding.JobFragmentBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobsFragment extends Fragment {

    private JobFragmentBinding binding;
    private SessionManager session;

    public JobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_jobs, container, false);

        session = new SessionManager(getActivity());
        String userType = session.getUserDetails().get(session.KEY_TYPE);
        if(!userType.equals("company")){
            binding.fab.setVisibility(View.INVISIBLE);
        }else {
            binding.emptySubtitleText.setText("Please click on + icon to add new jobs.");
            binding.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Utils.startIntent(getActivity(),JobEditorActivity.class);
                    Intent intent = new Intent(getActivity(),JobEditorActivity.class);
                    startActivity(intent);
                }
            });
        }
        binding.jobList.setEmptyView(binding.emptyView);

        return binding.getRoot();
    }

}
