package com.example.muhammad.practice;


import android.content.ContentUris;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.ListAdapter.JobListAdapter;
import com.example.muhammad.practice.ListAdapter.UserAdapter;
import com.example.muhammad.practice.SessionManager.SessionManager;
import com.example.muhammad.practice.databinding.JobFragmentBinding;
import com.example.muhammad.practice.modal.Jobs;
import com.example.muhammad.practice.modal.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JobsFragment extends Fragment {

    private JobFragmentBinding binding;
    private SessionManager session;
    private DatabaseReference mDatabase;
    private FirebaseUtils firebaseUtils;

    public JobsFragment() {
        // Required empty public constructor
        firebaseUtils = new FirebaseUtils();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_jobs, container, false);

        session = new SessionManager(getActivity());
        String userType = session.getUserDetails().get(session.KEY_TYPE);
        String userId = session.getUserDetails().get(session.KEY_ID);
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

        final ArrayList<Jobs> list = new ArrayList<Jobs>();
        //list.add(new Users("ddd","student","abc@abc.com","Muhammad"));


        final JobListAdapter adapter = new JobListAdapter(getActivity(),list);
        binding.jobList.setAdapter(adapter);
        binding.jobList.setEmptyView(binding.emptyView);

        binding.jobList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Jobs selectedJob = adapter.getItem(i);

                Intent r = new Intent(getContext(),JobEditorActivity.class);
                r.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                r.putExtra("job_id", selectedJob.getJobId());


                startActivity(r);


            }
        });



        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(userType.equals("company")) {
            mDatabase.child("Jobs").orderByChild("statusWithCompanyId").equalTo(userId+"_1").

                    addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Jobs job = dataSnapshot.getValue(Jobs.class);
                    job.setJobId(dataSnapshot.getKey());
                    adapter.add(job);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            mDatabase.child("Jobs").orderByChild("jobStatus").equalTo(1).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Jobs job = dataSnapshot.getValue(Jobs.class);
                    job.setJobId(dataSnapshot.getKey());
                    adapter.add(job);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }



        return binding.getRoot();
    }

}
