package com.codewithamrit.myapplication;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.codewithamrit.myapplication.ForgetPassword.ForgetPasswordFirst;

public class ForgetPasswordActivity extends AppCompatActivity {
    FrameLayout fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        fragment_container=findViewById(R.id.fragment_forget_password);
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_forget_password, ForgetPasswordFirst.class,null)
                .commit();
    }
}