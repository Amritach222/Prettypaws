package com.codewithamrit.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;
import com.codewithamrit.myapplication.HandleDatabase.DatabaseHelper;
import com.codewithamrit.myapplication.HandleDatabase.VolleyCallBack;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;
import com.codewithamrit.myapplication.session.SessionManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    TextView SignUp;
    EditText etemail,etpassword;
    String url=NetworkIP.NETWORK_URL +"/dogFinderApp/login.php";
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private SessionManager sessionManager;
    private ImageView backbtn;
    TextView forgetPassword;
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if( user!= null ){
            Intent intent = new Intent(getApplicationContext(),ExploreActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Initialize SessionManager
        sessionManager= new SessionManager(getApplicationContext());
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // For SignUp
        SignUp= findViewById(R.id.sign_up);
        // assigning id
        etemail=findViewById(R.id.loginemail);
        etpassword=findViewById(R.id.loginpassword);
        backbtn=findViewById(R.id.back_btn);
        forgetPassword=findViewById(R.id.forget_password);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ForgetPasswordActivity.class));
            }
        });
        // On clicking back button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressBackBtn();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });
//        Sign in using google account
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });

    }
// pressing back btn
    private void pressBackBtn() {
        startActivity( new Intent(getApplicationContext(),ExploreActivity.class));

    }

    private void updateUI(GoogleSignInAccount account) {
        Log.d("email", "updateUI: "+account.getEmail());
    }

    private void getUserInformation() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            Log.d("name", "getUserInformation: "+personEmail);
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("amrit", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    // Login Process Here
    public void UserLogin(View view){
        final ProgressDialog progressDialog= new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle("Please wait...");
        final String email=etemail.getText().toString().trim();
        final String password=etpassword.getText().toString().trim();
        // Check if empty
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "You must provide email and password ", Toast.LENGTH_SHORT).show();

        }
        // Otherwise
        else
        {
            progressDialog.show();
            // use Volley StringRequest
            StringRequest request= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response", "onResponse: "+response);
                    try {
                        JSONObject object = new JSONObject(response);
                        String success=object.getString("success");
                        Log.d("check", "onResponse: "+success);
                        if(success.equals("1")){
                            progressDialog.dismiss();
                           Toast.makeText(SignInActivity.this, "login success", Toast.LENGTH_SHORT).show();
                            //store login in session
                            sessionManager.setLogin(true);
                            //store username in session
                            sessionManager.setEmail(email);
                            //store password in session
                            sessionManager.setPassword(password);
                            //Set Profile
                            DatabaseHelper helper= new DatabaseHelper();
                            helper.databaseHelper(getApplicationContext(), sessionManager.getEmail(), sessionManager.getPassword(), new VolleyCallBack() {
                                @Override
                                public void onSuccessResponse(List<UserGetterSetter> list) {

                                }
                            });
                            //Redirect Activit
                            startActivity( new Intent(SignInActivity.this,ExploreActivity.class));
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(SignInActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params= new HashMap<String, String>();
                    params.put("email",email);
                    params.put("password",password);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this,null);
            requestQueue.add(request);
        }


    }
}
