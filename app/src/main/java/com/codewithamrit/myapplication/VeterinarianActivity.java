package com.codewithamrit.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithamrit.myapplication.GetterSetter.ModalVeterinary;
import com.codewithamrit.myapplication.HandleDatabase.CallBackVeterinaryInfo;
import com.codewithamrit.myapplication.HandleDatabase.DatabaseVeterinary;
import com.codewithamrit.myapplication.RecyclerViewAdapter.RecyclerViewVeterinary;

import java.util.List;

public class VeterinarianActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewVeterinary viewVeterinary;
    ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinarian);
        back_btn=findViewById(R.id.back_btn);
        recyclerView=findViewById(R.id.recycler_view_veterinary);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
        setupAdapter();
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),ExploreActivity.class));
            }
        });
    }

    private void setupAdapter() {
        DatabaseVeterinary databaseVeterinary= new DatabaseVeterinary();
        databaseVeterinary.retrieveDetail(getApplicationContext(), new CallBackVeterinaryInfo() {
            @Override
            public void onResponse(List<ModalVeterinary> list) {
               viewVeterinary = new RecyclerViewVeterinary(getApplicationContext(),list);
               recyclerView.setAdapter(viewVeterinary);
            }
        });
    }
}