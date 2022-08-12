package com.codewithamrit.myapplication.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;

import java.util.List;

public class SessionManager {
    // Initializing variables
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("AppKey", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }
    public  void setLogin(boolean checklogin){
        editor.putBoolean("Key_login",checklogin);
        editor.apply();
    }
    public boolean getLogin(){
        return sharedPreferences.getBoolean("Key_login",false);
    }
    public void setEmail(String email){
        editor.putString("session_email",email);
        editor.apply();
    }
    public String getEmail(){
        return sharedPreferences.getString("session_email","");
    }
    public void setPassword(String password){
        editor.putString("session_password",password);
        editor.apply();
    }
    public String getPassword(){
        return sharedPreferences.getString("session_password","");
    }
    public void setAccountInfo(List<UserGetterSetter> setters){
        editor.putString("test_user",setters.toString());
        editor.apply();
    }
    public  void setUserId(final String userId){
        editor.putString("Key_Id",userId);
        editor.apply();
    }
    public String getUserId(){
        return sharedPreferences.getString("Key_Id","");
    }
}
