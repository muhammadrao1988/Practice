package com.example.muhammad.practice;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.databinding.SignUpCompanyBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanySignUpFragment extends Fragment {

    private SignUpCompanyBinding binding;

    public CompanySignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_company_sign_up, container, false);
        binding.signUpCompanyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtils firebaseUtils = new FirebaseUtils();
                String userType = "company";
                String email = binding.signUpEmail.getText().toString();
                String password = binding.signUpPassword.getText().toString();
                String confirmPassword = binding.signUpRepeatPassword.getText().toString();
                String name =  binding.companyName.getText().toString();

                if(Utils.checkStringEmpty(email) && Utils.checkStringEmpty(password) && Utils.checkStringEmpty(name)
                        && Utils.comparePassword(password,confirmPassword) ) {
                    firebaseUtils.fireBaseSignUp(email, password, name, "", getContext(), userType);
                }else{
                    Toast.makeText(getContext(),"Please fill out all fields and password and confirm password should be same",Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.loginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.startIntent(getContext(),Login.class);

            }
        });

        return binding.getRoot();
    }

}
