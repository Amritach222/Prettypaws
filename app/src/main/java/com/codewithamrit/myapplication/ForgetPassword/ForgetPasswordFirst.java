package com.codewithamrit.myapplication.ForgetPassword;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.codewithamrit.myapplication.HandleDatabase.CallBackCheckLike;
import com.codewithamrit.myapplication.HandleDatabase.CheckResetEmail;
import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.Utility.Config;
import com.codewithamrit.myapplication.Utility.SendMail;

import java.util.Random;

public class ForgetPasswordFirst extends Fragment {
    ImageButton ic_send;
    EditText resetEmail;
    private ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forget_password_first, container, false);

        ic_send=view.findViewById(R.id.ic_send);
        resetEmail=view.findViewById(R.id.fp_email);
        ic_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        return view;
    }
    private void sendEmail() {
        int min=10000;
        int max=99999;
        Random random= new Random();
        int randomNumber=random.nextInt(max-min)+min;
        String email=resetEmail.getText().toString().trim();
        String subject="Reset Your Password";
        String message="To reset your password enter the following verification code: \n "+randomNumber;

        if (validateEmail(email)==true){
            progressDialog = ProgressDialog.show(getContext(),"Verifying email","Please wait...",false,false);
            new CheckResetEmail().checkResetEmail(getContext(), email, new CallBackCheckLike() {
                @Override
                public void onSuccessResponse(boolean checkemail) {
                    if (checkemail==true){
                        progressDialog.dismiss();
                        SendMail sm = new SendMail(getContext(), email, subject, message, new CallBackCheckLike() {
                            @Override
                            public void onSuccessResponse(boolean checklike) {
                               if (checklike==true){
                                   Config.V_CODE =""+randomNumber;
                                   Config.V_EMAIL=email;
                                   getActivity().getSupportFragmentManager()
                                           .beginTransaction()
                                           .setReorderingAllowed(true)
                                           .replace(R.id.fragment_forget_password,ForgetPasswordVerification.class,null)
                                           .commit();
                               }
                               else{
                                   Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                               }
                            }
                        });
                        sm.execute();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Enter your registered email.", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
    // Validate Email
    private  Boolean validateEmail(String email){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.matches(emailPattern)){
            return  true;
        }
        else if(email.isEmpty()){
            Toast.makeText(getContext(), "Enter email", Toast.LENGTH_SHORT).show();
            return  false;
        }
        else{
            Toast.makeText(getContext(), "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}