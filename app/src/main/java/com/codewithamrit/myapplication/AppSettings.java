package com.codewithamrit.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

public class AppSettings extends AppCompatActivity {
    NavigationView navigationView_first, navigationView_second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        navigationView_first=findViewById(R.id.navigation_view_setting_first);
        navigationView_second=findViewById(R.id.navigation_view_setting_second);
        navigationView_first.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.login_menu:
                        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                        break;
                    case R.id.sign_menu:
                        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                        break;
                    case R.id.forgetpass_menu:
                        startActivity(new Intent(getApplicationContext(),ForgetPasswordActivity.class));
                        break;
                }
                return false;
            }
        });

    }
}