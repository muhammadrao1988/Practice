package com.example.muhammad.practice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.ListAdapter.UserAdapter;
import com.example.muhammad.practice.databinding.CompanyFragmentBinding;
import com.example.muhammad.practice.databinding.StudentFragmentBinding;
import com.example.muhammad.practice.modal.Users;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class CompanyFragment extends Fragment {

    private CompanyFragmentBinding binding;
    private FirebaseUtils firebaseUtils;
    private DatabaseReference mDatabase;

    public CompanyFragment() {
        // Required empty public constructor
        firebaseUtils = new FirebaseUtils();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_company, container, false);


        final ArrayList<Users> list = new ArrayList<Users>();
        //list.add(new Users("ddd","student","abc@abc.com","Muhammad"));


        final UserAdapter adapter = new UserAdapter(getActivity(),list);
        binding.companyList.setAdapter(adapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Users").orderByChild("mType").equalTo("company").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Users user = dataSnapshot.getValue(Users.class);
                adapter.add(user);
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


        return binding.getRoot();
    }



}
