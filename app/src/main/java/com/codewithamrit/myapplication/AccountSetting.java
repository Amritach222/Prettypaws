package com.codewithamrit.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.codewithamrit.myapplication.CompressImage.ImageCompression;
import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;
import com.codewithamrit.myapplication.HandleDatabase.DatabaseHelper;
import com.codewithamrit.myapplication.HandleDatabase.VolleyCallBack;
import com.codewithamrit.myapplication.session.SessionManager;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountSetting extends AppCompatActivity implements View.OnClickListener{
    private static final int PICK_IMAGE_REQUEST = 100 ;
    private ImageButton change_image;
    private Uri imagePath;
    private Bitmap bitmap, imageToStore;
    private String encodedImage;
    private CircleImageView accountImage;
    private TextView update_name,update_email,update_contact;
    private TextView dialog_title,dialog_body,error_message;
    private EditText update_account,old_password,new_password,confirm_password;
    private Button btn_cancel,btn_save,btn_cancel_pass,btn_save_pass;
    private SessionManager sessionManager;
    DatabaseHelper helper;
    private Dialog dialog,dialog1;
    private ImageButton backBtn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        // Assigning ID
        update_name=findViewById(R.id.update_name);
        update_email=findViewById(R.id.update_email);
        update_contact=findViewById(R.id.update_contact);
        change_image=findViewById(R.id.update_account_image);
        accountImage=findViewById(R.id.account_image);
        backBtn=findViewById(R.id.account_back);
//        Click to back
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileFragment.class));
            }
        });
//        Creating object of Dialog
        dialog= new Dialog(AccountSetting.this);
        dialog1= new Dialog(AccountSetting.this);
        dialog.setContentView(R.layout.dialog_update_account);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        dialog.getWindow().getAttributes().windowAnimations=R.style.dialog_animation;
//        Assign id of dialog ViewElements
        dialog_title=dialog.findViewById(R.id.dialog_title);
        dialog_body=dialog.findViewById(R.id.dialog_body);
        update_account=dialog.findViewById(R.id.change_account);
        btn_cancel=dialog.findViewById(R.id.cancel_btn);
        btn_save=dialog.findViewById(R.id.save_btn);
//         assign object of sessionManager
        sessionManager= new SessionManager(getApplicationContext());
//        Creating object of DatabaseHelper
        helper= new DatabaseHelper();
//        Set information to the profile
        if(sessionManager.getLogin()==true){
            String email= sessionManager.getEmail();
            String password= sessionManager.getPassword();
            DatabaseHelper databaseHelper=new DatabaseHelper();
            databaseHelper.databaseHelper(getApplicationContext(), email, password, new VolleyCallBack() {
                @Override
                public void onSuccessResponse(List<UserGetterSetter> list) {
                    for(UserGetterSetter setter:list){
                        update_name.setText(setter.getName());
                        update_email.setText(setter.getEmail());
                        update_contact.setText(setter.getContact());
                        Picasso.get().load(setter.getUserImage()).into(accountImage);
                    }
                }
            });
        }
        //changeImage
        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMyImage();
            }
        });


    }
// Update my image
    private void updateMyImage() {
//        Dexter.withActivity(AccountSetting.this)
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent= new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE_REQUEST);
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//
//                    }
//                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null){
            imagePath= data.getData();
            try {
                InputStream inputStream= getContentResolver().openInputStream(imagePath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                imageToStore= ImageCompression.resizeImage(bitmap,240000);
                imageToStore(imageToStore);
                accountImage.setImageBitmap(imageToStore);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
 // Prepare encoded image
    private void imageToStore(Bitmap bitmap) {
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] ByteArray=stream.toByteArray();
        encodedImage=android.util.Base64.encodeToString(ByteArray, Base64.DEFAULT);
        helper.storeAccountImage(getApplicationContext(),encodedImage,sessionManager.getEmail(),sessionManager.getPassword());
    }

    @Override
    public void onClick(View view) {
        final String oldemail=sessionManager.getEmail();
        final String oldpassword=sessionManager.getPassword();
        switch (view.getId()){
            case R.id.edit_name_btn:
                //Code Here
                dialog_title.setText("Name");
                dialog_body.setText("Enter your name");
                dialog.show();
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name=update_account.getText().toString().trim();
                        helper.updateProfile(getApplicationContext(),oldemail,oldpassword,name,"1","1","1");
                        dialog.dismiss();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.edit_email_btn:
                //Code Here
                dialog_title.setText("Email");
                dialog_body.setText("Enter your email");
                dialog.show();
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email=update_account.getText().toString().trim();
                        helper.updateProfile(getApplicationContext(),oldemail,oldpassword,"1",email,"1","1");
                        dialog.dismiss();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.edit_contact_btn:
                //Code Here
                dialog_title.setText("Contact");
                dialog_body.setText("Enter your contact number");
                dialog.show();
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String contact=update_account.getText().toString().trim();
                        helper.updateProfile(getApplicationContext(),oldemail,oldpassword,"1","1",contact,"1");
                        dialog.dismiss();
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.edit_password_btn:
                //Code Here
                dialog1.setContentView(R.layout.dialog_update_password);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog1.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_background));
                }
                dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog1.setCancelable(false);
                dialog1.getWindow().getAttributes().windowAnimations=R.style.dialog_animation;
                dialog_title=dialog1.findViewById(R.id.dialog_title);
                old_password=dialog1.findViewById(R.id.old_password);
                new_password=dialog1.findViewById(R.id.new_password);
                confirm_password=dialog1.findViewById(R.id.confirm_password);
                error_message=dialog1.findViewById(R.id.error_message);
                btn_cancel_pass=dialog1.findViewById(R.id.cancel_btn);
                btn_save_pass=dialog1.findViewById(R.id.save_btn);
                dialog_title.setText("Password");
                error_message.setTextColor(getResources().getColor(R.color.color_red));
                dialog1.show();
                btn_save_pass.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        String old_pass=old_password.getText().toString().trim();
                        String new_pass=new_password.getText().toString().trim();
                        String confirm_pass=confirm_password.getText().toString().trim();
                        if(old_pass.isEmpty() || new_pass.isEmpty() || confirm_pass.isEmpty()){
                            error_message.setText("There are empty fields!");
                        }
                        else {


                            if (old_pass.equals(sessionManager.getPassword()) == false) {
                                error_message.setText("No matching old password");
                            } else if (new_pass.equals(old_pass)) {
                                error_message.setText("New password cannot be same as old password");
                            } else if (new_pass.equals(confirm_pass) == false) {
                                error_message.setText("Password doesn't match");
                            } else {
                                helper.updateProfile(getApplicationContext(),oldemail,oldpassword,"1","1","1",new_pass);
                                dialog1.dismiss();
                            }
                        }

                    }
                });
                btn_cancel_pass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                break;
            default:
                Toast.makeText(this, "Wrong button Selected", Toast.LENGTH_SHORT).show();

        }
    }

}