package com.example.muhammad.practice.ListAdapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.muhammad.practice.R;
import com.example.muhammad.practice.databinding.UserListBinding;
import com.example.muhammad.practice.modal.Users;

import java.util.ArrayList;

/**
 * Created by Muhammad on 2/18/2017.
 */
public class UserAdapter extends ArrayAdapter<Users> {
    private ArrayList<Users> list;
    private UserListBinding binding;
    private Context c;
    public UserAdapter(Context context, ArrayList<Users> userList) {
        super(context, 0, userList);
        this.list = userList;
        this.c = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        // to save memory
        if(view == null) {
//            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            binding = DataBindingUtil.inflate(LayoutInflater.from(c), R.layout.user_list_item, parent,false);
        }else{
            view = binding.getRoot();
        }
        Users s = list.get(position);


        binding.name.setText(s.getmName());



        //return view;
        return binding.getRoot();
    }
}
