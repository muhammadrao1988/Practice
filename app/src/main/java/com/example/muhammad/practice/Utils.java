package com.example.muhammad.practice;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Muhammad on 2/14/2017.
 */
public class Utils {

    public static void startIntent(Context context,Class cls){

        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static boolean checkStringEmpty(String userInput){
        if(userInput.equals("") || userInput==null){
            return false;
        }else {
            return true;
        }

    }

    public static boolean comparePassword(String password, String comparePassword){
        if(password.equals("") || password==null){
            return false;
        }else if(password.equals(comparePassword)) {
            return true;
        }else{
            return false;
        }

    }

    public static boolean validUsertype(String type){
        if(type.equals("student") ||type.equals("company") || type.equals("admin")){
            return  true;
        }else{
            return false;
        }
    }
}
