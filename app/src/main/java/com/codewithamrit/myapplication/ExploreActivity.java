package com.codewithamrit.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;
import com.codewithamrit.myapplication.HandleDatabase.CallBackUpdates;
import com.codewithamrit.myapplication.HandleDatabase.DatabaseHelper;
import com.codewithamrit.myapplication.HandleDatabase.ManageUpdates;
import com.codewithamrit.myapplication.HandleDatabase.VolleyCallBack;
import com.codewithamrit.myapplication.NavigationButtonFragments.FavouriteFragment;
import com.codewithamrit.myapplication.NavigationButtonFragments.HomeFragment;
import com.codewithamrit.myapplication.NavigationButtonFragments.UpdateFragments;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;
import com.codewithamrit.myapplication.Utility.NetworkChangeListener;
import com.codewithamrit.myapplication.session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExploreActivity extends AppCompatActivity {
    // Declaring variable
 private SessionManager sessionManager;
    private DrawerLayout drawerLayout;
    private ImageButton acc_btn;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private TextView full_name,user_address;
    private CircleImageView profileImage;
    private TextView logout;
    LinearLayout setting;
    NetworkChangeListener listener= new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        sessionManager= new SessionManager(getApplicationContext());
        full_name=findViewById(R.id.user_fullName);
        user_address=findViewById(R.id.user_address);
        logout=findViewById(R.id.logout);
        setting=findViewById(R.id.setting);
        //Creating object of DatabaseHelper
        DatabaseHelper helper= new DatabaseHelper();
        // Initializing variable
        acc_btn= (ImageButton)findViewById(R.id.account_btn);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.drawer_navigation_view);
        profileImage=findViewById(R.id.profileImage);
        // Check Notification
        ManageUpdates updates = new ManageUpdates();
        updates.updates(getApplicationContext(), new SessionManager(getApplicationContext()).getUserId(), NetworkIP.NETWORK_URL
                + "/dogFinderApp/checkupdates.php", new CallBackUpdates() {
            @Override
            public void onResponse(List<String> list) {
                UpdateFragments.updates=list;
            }
        });
        //Check Login Condition
        if (sessionManager.getLogin()==true){
            String email= sessionManager.getEmail();
            String password=sessionManager.getPassword();
            helper.databaseHelper(getApplicationContext(), email, password, new VolleyCallBack() {
                @Override
                public void onSuccessResponse(List<UserGetterSetter> list) {
                    for(UserGetterSetter setter:list){
                        full_name.setText(setter.getName());
                        user_address.setText(setter.getAddress());
                        Picasso.get().load(setter.getUserImage()).into(profileImage);
                    }
                }
            });
        }
//        Logout from an account
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setLogin(false);
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                finish();
            }
        });
//        Set onClickListener on setting
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(),AppSettings.class));
            }
        });


        // Open Profile
        acc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileFragment.class));
            }
        });
         bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_navigation_buttons);
             //set onclick listener
             getSupportFragmentManager()
                     .beginTransaction()
                     .setReorderingAllowed(true)
                     .add(R.id.navigation_container, HomeFragment.class,null)
                     .commit();



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.explore:
                        //open Home Fragment
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.navigation_container, HomeFragment.class,null)
                                .commit();
                        Menu menu1=bottomNavigationView.getMenu();
                        MenuItem menuItem1=menu1.getItem(0);
                        menuItem1.setChecked(true);
                        break;
                    case R.id.favourite:
                        //Open Favourite Fragment
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.navigation_container, FavouriteFragment.class,null)
                                .commit();
                        Menu menu2=bottomNavigationView.getMenu();
                        MenuItem menuItem2=menu2.getItem(1);
                        menuItem2.setChecked(true);
                        break;
                    case R.id.update:
                        // Open Update Fragment
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.navigation_container, UpdateFragments.class,null)
                                .commit();
                        Menu menu3=bottomNavigationView.getMenu();
                        MenuItem menuItem3=menu3.getItem(2);
                        menuItem3.setChecked(true);
                        break;
                }
                return false;
            }
        });
        // Working with Drawer Items
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.draw_profile:
                        if(sessionManager.getLogin()==true){
                            item.setChecked(true);
                            //Profile Here
                            startActivity(new Intent(getApplicationContext(),ProfileFragment.class));
                            drawerLayout.closeDrawers();

                        }
                        else{
//                            Redirect to login
                            startActivity( new Intent(getApplicationContext(),SignInActivity.class));
                        }

                        return true;
                    case R.id.add_dog:
                        if(sessionManager.getLogin()==true){
                            item.setChecked(true);
                            //add dog from here
                            startActivity(new Intent(getApplicationContext(),AddDogActivity.class));

                            drawerLayout.closeDrawers();
                        }
                        else{
//                            Redirect to login
                            startActivity( new Intent(getApplicationContext(),SignInActivity.class));
                        }
                        return true;
                    case R.id.donation:
                        if(sessionManager.getLogin()==true){
                            item.setChecked(true);
                            //Donate from here
                            startActivity( new Intent(getApplicationContext(),DonationActivity.class));
                            //DatabaseHelper helper= new DatabaseHelper(ExploreActivity.this,"amritach22@gmail.com","amrit@123");
                            drawerLayout.closeDrawers();
                        }
                        else{
//                            Redirect to login
                            startActivity( new Intent(getApplicationContext(),SignInActivity.class));
                        }
                        return true;
                    case R.id.find_veterinary:
                            startActivity( new Intent(getApplicationContext(),VeterinarianActivity.class));
                        return true;
                    case R.id.question_and_suggestion:
                        item.setChecked(true);
                        //Questions and suggestion from here
                        startActivity(new Intent(getApplicationContext(),SendFeedBack.class));

                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.share:
                        item.setChecked(true);
                        //Share from here

                        shareApp();

                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.rate:
                        item.setChecked(true);
                        //Rating from here
                        rateUs();

                        drawerLayout.closeDrawers();
                        return true;


                }
                return false;
            }
        });


    }

    public void ClickMenu(View view){
        //Open Drawer
        MainActivity.openDrawer(drawerLayout);
    }
    //For sharing app
    public void shareApp(){
        try {
            //create the sharing intent
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/message");
            String shareBody = "https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent,"Share Via"));

        }
        catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    //For Rate Option
    public void rateUs(){
        try {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
            Intent intent= new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);

        }catch (Exception e){
            Toast.makeText(this, "Unable to Open "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        IntentFilter filter= new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(listener,filter);
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(listener);
        super.onStop();
    }
    public  void onUpdateUserListener(){

    }
}
