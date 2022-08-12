package com.codewithamrit.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;
import com.codewithamrit.myapplication.HandleDatabase.DatabaseHelper;
import com.codewithamrit.myapplication.HandleDatabase.VolleyCallBack;
import com.codewithamrit.myapplication.session.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends AppCompatActivity {
    private LinearLayout account_setting;
    private TextView account_name,account_address,account_email;
    private SessionManager sessionManager;
    private CircleImageView profileImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        //Assign Id
        account_setting=findViewById(R.id.account_setting);
        account_name=findViewById(R.id.account_name);
        account_email=findViewById(R.id.account_email);
        account_address=findViewById(R.id.account_address);
        profileImage=findViewById(R.id.imageView_profile);
        //Object of sessionManager
        sessionManager= new SessionManager(getApplicationContext());
        if (sessionManager.getLogin()==true){
            setInformation();
        }
        else{
            Toast.makeText(this, "Please Login first", Toast.LENGTH_SHORT).show();
            startActivity( new Intent(getApplicationContext(),SignInActivity.class));
            finish();
        }
        account_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAccountSetting();
            }
        });


    }
//Set all information
    private void setInformation() {
        DatabaseHelper helper=new DatabaseHelper();
        helper.databaseHelper(getApplicationContext(), sessionManager.getEmail(), sessionManager.getPassword(), new VolleyCallBack() {
            @Override
            public void onSuccessResponse(List<UserGetterSetter> list) {
                for(UserGetterSetter users:list){
                    account_name.setText(users.getName());
                    account_email.setText(users.getEmail());
                    account_address.setText(users.getAddress());
                    Picasso.get().load(users.getUserImage()).into(profileImage);
                }
            }
        });
    }

    private void openAccountSetting() {
        startActivity(new Intent(getApplicationContext(),AccountSetting.class));
    }
    public  void logOut(View view){
        sessionManager.setLogin(false);
        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
        finish();
    }

}