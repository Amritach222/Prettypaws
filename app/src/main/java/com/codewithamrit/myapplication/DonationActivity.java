package com.codewithamrit.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codewithamrit.myapplication.NetworkIP.NetworkIP;
import com.esewa.android.sdk.payment.ESewaConfiguration;
import com.esewa.android.sdk.payment.ESewaPayment;
import com.esewa.android.sdk.payment.ESewaPaymentActivity;

import static android.content.ContentValues.TAG;

public class DonationActivity extends AppCompatActivity {
    EditText amount;
    String productName="Donation";
    String productId="PrettyPaws";
    String callbackUrl= NetworkIP.NETWORK_URL+"/dogFinderApp/donate_success.html";
    public static int REQUEST_CODE_PAYMENT=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        amount=findViewById(R.id.amount);
        Button buttonBuy = (Button) findViewById(R.id.button_buy);
        ESewaConfiguration eSewaConfiguration= new ESewaConfiguration()
                .clientId("JB0BBQ4aD0UqIThFJwAKBgAXEUkEGQUBBAwdOgABHD4DChwUAB0R")
                .secretKey("BhwIWQQADhIYSxILExMcAgFXFhcOBwAKBgAXEQ==")
                .environment(ESewaConfiguration.ENVIRONMENT_TEST);
        buttonBuy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String amtValue=amount.getText().toString().trim();
                if (amtValue.isEmpty()==true){
                    Toast.makeText(DonationActivity.this, "Please Enter the amount", Toast.LENGTH_SHORT).show();

                }
                else {
                    ESewaPayment eSewaPayment = new ESewaPayment(amtValue,productName,productId,callbackUrl);
                    Intent intent = new Intent(DonationActivity.this, ESewaPaymentActivity.class);
                    intent.putExtra(ESewaConfiguration.ESEWA_CONFIGURATION, eSewaConfiguration);

                    intent.putExtra(ESewaPayment.ESEWA_PAYMENT, eSewaPayment);
                    startActivityForResult(intent, REQUEST_CODE_PAYMENT);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) return;
                String message = data.getStringExtra(ESewaPayment.EXTRA_RESULT_MESSAGE);
                Log.i(TAG, "Proof of Payment " + message);
                Toast.makeText(this, "SUCCESSFUL PAYMENT", Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled By User", Toast.LENGTH_SHORT).show();
            } else if (resultCode == ESewaPayment.RESULT_EXTRAS_INVALID) {
                if (data == null) return;
                String message = data.getStringExtra(ESewaPayment.EXTRA_RESULT_MESSAGE);
                Log.i(TAG, "Proof of Payment " + message);
            }
        }
    }
}