package com.codewithamrit.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    // Variable declaration
    Button btn_signUp;
    EditText signupName,signupEmail,signUpContactNumber,signupAddress,signupPassword,signupConfirm_password;
    TextInputLayout nameLayout,emailLayout,numberLayout,addressLayout,passwordLayout,confirm_passwordLayout;
    String url=NetworkIP.NETWORK_URL +"/dogFinderApp/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        assigning variable
        btn_signUp= findViewById(R.id.sign_up_button);
        signupName= findViewById(R.id.name);
        nameLayout=  findViewById(R.id.nameLayout);
        signupEmail= findViewById(R.id.email);
        emailLayout=findViewById(R.id.emailLayout);
        signUpContactNumber=findViewById(R.id.contact_number);
        numberLayout=findViewById(R.id.contactLayout);
        signupAddress= findViewById(R.id.personAddress);
        addressLayout=findViewById(R.id.addressLayout);
        signupPassword=findViewById(R.id.password);
        passwordLayout=findViewById(R.id.passwordLayout);
        signupConfirm_password=findViewById(R.id.confirm_password);
        confirm_passwordLayout=findViewById(R.id.confirm_password_Layout);

        // Create an Action while sign up button is clicked
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // getting values
                final String name=signupName.getText().toString().trim();
                final String email=signupEmail.getText().toString().trim();
                final String address=signupAddress.getText().toString().trim();
                final String password=signupPassword.getText().toString().trim();
                final String contact_number=signUpContactNumber.getText().toString().trim();
                //Creating processDialog
                final ProgressDialog progressDialog= new ProgressDialog(SignUpActivity.this);
                progressDialog.setTitle("Please wait!");
                if( validateName() && validateEmail() && validateNumber() && validateAddress() && validatePassword()){
                    String confirm_password=signupConfirm_password.getText().toString().trim();
                    if(confirm_password.equals(password)){
                        // Process to register person to the database using volley library
                        progressDialog.show();
                        // creating Object of StringRequest
                        StringRequest stringRequest= new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    String success= object.getString("success");
                                    if(success.equals("1")) {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUpActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                                    }
                                    else {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }}, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                progressDialog.dismiss();
                                Log.d("name", ""+volleyError.getMessage());
                                Toast.makeText(SignUpActivity.this, ""+volleyError.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params= new HashMap<String, String>();
                                params.put("name",name);
                                params.put("email",email);
                                params.put("contact_number",contact_number);
                                params.put("address",address);
                                params.put("password",password);
                                return params;
                            }
                        };
                        //Create requestQueue Object
                        RequestQueue requestQueue= Volley.newRequestQueue(SignUpActivity.this,null);
                        requestQueue.add(stringRequest);
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Error Triggered", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    // Validate Name
    private Boolean validateName(){
        String name= signupName.getText().toString().trim();
        String nameval= "^[0-9]+$"+"(?=.*[@$#%^&+-])";
        if (name.matches(nameval)){
            nameLayout.setError("Invalid Name");
            return  false;
        }
        else if(name.isEmpty()){
            nameLayout.setError("Can't be empty!");
            return false;
        }
        else{
            nameLayout.setError(null);
            return  true;

        }

    }
    // Validate Email
    private  Boolean validateEmail(){
        String email=signupEmail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.matches(emailPattern)){
            emailLayout.setError(null);
            return  true;
        }
        else if(email.isEmpty()){
            emailLayout.setError("Can't be empty!");
            return  false;
        }
        else{
            emailLayout.setError("Invalid Email");
            return false;
        }

    }

    //Validate PhoneNumber
    private Boolean validateNumber(){
        String contact_number=signUpContactNumber.getText().toString().trim();
        String numberPattern = "^\\d{10}$";
        if(contact_number.matches(numberPattern)){
            numberLayout.setError(null);
            return true;

        }
        else if(contact_number.isEmpty()==true){
            numberLayout.setError("can't be empty");
            return  false;

        }
        else{
            numberLayout.setError("Invalid number");
            return false;
        }

    }
    // Validate Address
    private Boolean validateAddress(){
        String address= signupAddress.getText().toString().trim();
        if(address.isEmpty()){
            addressLayout.setError("Can't be empty!");
            return false;
        }
        else{
            addressLayout.setError(null);
            return true;
        }

    }
    // Validate password
    private Boolean validatePassword(){
        String password= signupPassword.getText().toString().trim();
        String passwordval="^"
                +"(?=.*[a-zA-Z])"// any character
                +"(?=.*[@$#%^&+-])" // special characters
                +"[^\\s-]"// no whiteSpace
                +".{8,}"//at least 8 characters
                +"$";
        if(password.isEmpty()){
            passwordLayout.setError("Can't ne empty");
            return  false;

        }
        else if(!password.matches(passwordval)){
            passwordLayout.setError("Password must have 8 characters and one special character!");
            return false;
        }
        else
        {
            passwordLayout.setError(null);
            return true;
        }

    }
    // Switch to login Activity after clicking on Already have an Account
    public void   switchToLogin(View view){
        startActivity( new Intent(SignUpActivity.this,SignInActivity.class));
    }
}