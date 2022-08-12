package com.codewithamrit.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.codewithamrit.myapplication.AddDogFragments.AddDogFragmentFirst;
import com.codewithamrit.myapplication.AddDogFragments.AddDogFragmentSecond;

public class AddDogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);
        // Use Fragment Manager
        if(savedInstanceState==null) {
            addFragment();
        }

    }
    private void addFragment(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container, AddDogFragmentFirst.class,null)
                .commit();

    }
    public void clickFAB1(View v){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, AddDogFragmentSecond.class,null).commit();
    }

}