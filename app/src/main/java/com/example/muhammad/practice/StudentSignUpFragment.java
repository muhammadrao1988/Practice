package com.example.muhammad.practice;



import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.muhammad.practice.Firebase.FirebaseUtils;
import com.example.muhammad.practice.databinding.SignUpStudentBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentSignUpFragment extends Fragment {
    private RadioGroup genderGroup;
    private SignUpStudentBinding binding;
    public StudentSignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // binding = DataBindingUtil.inflate(inflater,R.layout.fragment_student_sign_up, container, false);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_student_sign_up, container, false);
        binding.signUpStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUtils firebaseUtils = new FirebaseUtils();
                String userType = "student";
                String email = binding.signUpEmail.getText().toString();
                String password = binding.signUpPassword.getText().toString();
                String confirmPassword = binding.signUpRepeatPassword.getText().toString();
                String name =  binding.studentName.getText().toString();
                String gender = "";
                boolean radioSelected = false;
                int selectedId = binding.gender.getCheckedRadioButtonId();
                if(selectedId > 0 ){
                    radioSelected = true;
                }
                if(binding.radioMale.isChecked()){
                    gender = "male";
                }else{
                    gender = "female";
                }
                if(Utils.checkStringEmpty(email) && Utils.checkStringEmpty(password) && Utils.checkStringEmpty(name)
                        && Utils.checkStringEmpty(gender) && Utils.comparePassword(password,confirmPassword) && radioSelected) {
                    firebaseUtils.fireBaseSignUp(email, password,  name, gender, getContext(), userType);
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
