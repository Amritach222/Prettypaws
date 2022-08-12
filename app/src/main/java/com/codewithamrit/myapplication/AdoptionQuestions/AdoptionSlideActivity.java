package com.codewithamrit.myapplication.AdoptionQuestions;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.codewithamrit.myapplication.R;

public class AdoptionSlideActivity extends AppCompatActivity {
    public static ViewPager viewPager;
    private static RadioButton getAnswer;
    private SlideViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_slide);
        Intent intent= getIntent();
        String id=intent.getStringExtra("id");
        viewPager=findViewById(R.id.viewpager);
        adapter= new SlideViewPagerAdapter(this,id);
        viewPager.setAdapter(adapter);

    }
}