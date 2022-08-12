package com.codewithamrit.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codewithamrit.myapplication.AdoptionQuestions.AdoptionSlideActivity;
import com.codewithamrit.myapplication.HandleDatabase.CallBackCheckLike;
import com.codewithamrit.myapplication.HandleDatabase.HandleLikeDislike;
import com.codewithamrit.myapplication.session.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PreviewDogActivity extends AppCompatActivity {
    // Global variable
    private ImageView dogImage;
    private TextView name,location,age,description, adoption_btn;
    private ImageButton genderImage;
    private ImageButton like_btn;
    private SessionManager sessionManager;
    private boolean clickLike=false;
    private ArrayList<String> dogInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_preview);
//        object of sessionManager
        sessionManager= new SessionManager(getApplicationContext());
//        Assigning id
        name=findViewById(R.id.dogName);
        dogImage=findViewById(R.id.dogImage);
        location=findViewById(R.id.address);
        age=findViewById(R.id.age);
        description=findViewById(R.id.dogDescription);
        genderImage=findViewById(R.id.gender);

//        Assigning id of like_btn and adoption btn
        adoption_btn=findViewById(R.id.adoption_btn);
        like_btn=findViewById(R.id.like_btn);

//        Getting Dog Information using arraylist from another activity
        dogInfo=(ArrayList<String>)getIntent().getSerializableExtra(Intent.EXTRA_TEXT);
//        setDogId
//       Invoke method for setting dog Information
        setDogInformation();

//        check status of like button and set icon to the button

            checkLikeStatus();

//        set onclick listener on Like button
        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.getLogin()==true){
                    setLikeIcon();
                }
                else{
                    startActivity( new Intent(getApplicationContext(),SignInActivity.class));
                    Toast.makeText(getApplicationContext(), "SignIn First", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        Set onclickListener on adoption button
        adoption_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.getLogin()==true){
                    Intent intent= new Intent(getApplicationContext(), AdoptionSlideActivity.class);
                    intent.putExtra("id",dogInfo.get(6));
                    startActivity(intent);
                }
                else{
                    startActivity( new Intent(getApplicationContext(),SignInActivity.class));
                    Toast.makeText(getApplicationContext(), "SignIn First", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
//    Setting dogInformation
    public void setDogInformation(){
        name.setText(dogInfo.get(0));
        Picasso.get().load(dogInfo.get(4)).into(dogImage);
        location.setText(dogInfo.get(3));
        age.setText(dogInfo.get(1));
        description.setText(dogInfo.get(5));
        if(dogInfo.get(2).equals("Male")){
            genderImage.setImageResource(R.drawable.male);
        }
        else {
            genderImage.setImageResource(R.drawable.female);
        }
    }

    // Pressing BackButton
    public void backToExplore(View view){
        startActivity(new Intent(this,ExploreActivity.class));
    }


//    Checking LikeStatus
    public void checkLikeStatus(){
        new HandleLikeDislike().checkLikeStatus(getApplicationContext(), sessionManager.getUserId(), dogInfo.get(6), new CallBackCheckLike() {
            @Override
            public void onSuccessResponse(boolean checklike) {
                if(checklike==true){
                    like_btn.setImageResource(R.drawable.ic_fav);
                }
            }
        });
    }


//    set onClickListener on like button
    public  void setLikeIcon(){
        clickLike=true;
        if(clickLike==true){
            new HandleLikeDislike().checkLikeStatus(getApplicationContext(), sessionManager.getUserId(), dogInfo.get(6), new CallBackCheckLike() {
                @Override
                public void onSuccessResponse(boolean checklike) {
                    if(checklike==true){
                        new HandleLikeDislike().deleteLike(getApplicationContext(), sessionManager.getUserId(), dogInfo.get(6), new CallBackCheckLike() {
                            @Override
                            public void onSuccessResponse(boolean checklike) {
                                if (checklike==true){
                                    like_btn.setImageResource(R.drawable.ic_favwhite);
                                    clickLike=false;
                                }
                            }
                        });
                    }
                    else{
                        new HandleLikeDislike().insertLike(getApplicationContext(), sessionManager.getUserId(), dogInfo.get(6), new CallBackCheckLike() {
                            @Override
                            public void onSuccessResponse(boolean checklike) {
                                if (checklike==true){
                                    like_btn.setImageResource(R.drawable.ic_fav);
                                    clickLike=false;
                                }
                            }
                        });
                    }
                }
            });
        }
    }

}
