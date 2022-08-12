package com.codewithamrit.myapplication.ForgetPassword;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.codewithamrit.myapplication.ExploreActivity;
import com.codewithamrit.myapplication.HandleDatabase.DatabaseHelper;
import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.Utility.Config;


public class ForgetPasswordChangePassword extends Fragment {
        EditText new_password,confirm_password;
        ImageButton check_btn;
        ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forget_password_change_password, container, false);
            new_password=view.findViewById(R.id.in_new_password);
            confirm_password=view.findViewById(R.id.in_confirm_password);
            check_btn=view.findViewById(R.id.ic_send);
            check_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String new_pass=new_password.getText().toString().trim();
                    String confirm_pass=confirm_password.getText().toString().trim();
                    if(validatePassword(new_pass).equals(true)){
                        progressDialog = ProgressDialog.show(getContext(),"Verifying email","Please wait...",false,false);
                        if (new_pass.equals(confirm_pass)){
                            new DatabaseHelper().updateProfile(getContext(), Config.V_EMAIL,new_pass,"1","1","1","1");
                            startActivity( new Intent(getContext(), ExploreActivity.class));
                            progressDialog.dismiss();
                        }
                        else{
                            Toast.makeText(getContext(), "password doesn't match", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }

                }
            });

        return view;
    }
    // Validate password
    private Boolean validatePassword(String password){
        String passwordval="^"
                +"(?=.*[a-zA-Z])"// any character
                +"(?=.*[@$#%^&+-])" // special characters
                +"[^\\s-]"// no whiteSpace
                +".{7,}"//at least 8 characters
                +"$";
        if(password.isEmpty()){
            Toast.makeText(getContext(), "Can't be empty!", Toast.LENGTH_SHORT).show();
            return  false;

        }
        else if(!password.matches(passwordval)){
            Toast.makeText(getContext(), "Password must have 8 characters and one special character!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }

    }
}