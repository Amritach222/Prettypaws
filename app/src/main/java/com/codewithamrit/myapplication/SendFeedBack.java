package com.codewithamrit.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendFeedBack extends AppCompatActivity {
    private EditText name,message;
    private Button feedbackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        assign id
        setContentView(R.layout.activity_send_feed_back);
        name=findViewById(R.id.feedback_name);
        message=findViewById(R.id.feedback_message);
        feedbackBtn=findViewById(R.id.send_feedback_btn);
        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback_name = name.getText().toString().trim();
                String feedback_message = message.getText().toString().trim();
                if (feedback_name.isEmpty() == true && feedback_message.isEmpty() == true) {
                    Toast.makeText(SendFeedBack.this, "Please fill the fields", Toast.LENGTH_SHORT).show();
            }
                else{
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/html");
                    i.putExtra(Intent.EXTRA_EMAIL, "supprotprettypaws@gmail.com");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App");
                    i.putExtra(Intent.EXTRA_TEXT, "Name: " + feedback_name + "\nMessage: " + feedback_message);
                    try {
                        startActivity(Intent.createChooser(i, "Please Select Email"));
                    } catch (Exception e) {
                        Toast.makeText(SendFeedBack.this, "Can't Open" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}