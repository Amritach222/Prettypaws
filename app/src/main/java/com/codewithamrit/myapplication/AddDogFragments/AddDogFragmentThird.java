package com.codewithamrit.myapplication.AddDogFragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.codewithamrit.myapplication.ExploreActivity;
import com.codewithamrit.myapplication.GetterSetter.ModalClassDog;
import com.codewithamrit.myapplication.GetterSetter.UserGetterSetter;
import com.codewithamrit.myapplication.HandleDatabase.DatabaseHelper;
import com.codewithamrit.myapplication.HandleDatabase.DogInformationDatabaseHelper;
import com.codewithamrit.myapplication.HandleDatabase.VolleyCallBack;
import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.session.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AddDogFragmentThird extends Fragment {
    private ItemViewModel viewModel;
    private EditText dog_breed,dog_location,dog_message;
    private  String dog_name,dog_age,dog_gender;
    private Bitmap image;
    private String encodedImage;
    private FloatingActionButton actionButton;
    private String user_id;
    private SessionManager sessionManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_dog_third, container, false);
        sessionManager= new SessionManager(getContext());
        actionButton=view.findViewById(R.id.fab3);
        dog_breed=view.findViewById(R.id.dog_breed);
        dog_location=view.findViewById(R.id.dog_location);
        dog_message=view.findViewById(R.id.dog_message);

//        Set onclick listener on floating action button
            actionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String breed=dog_breed.getText().toString().trim();
                    String location=dog_location.getText().toString().trim();
                    String message= dog_message.getText().toString().trim();
                    //         Check if it is empty
                    if(location.isEmpty() || message.isEmpty()){
                        Toast.makeText(getContext(), " * fields are compulsory", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        DogInformationDatabaseHelper databaseHelper= new DogInformationDatabaseHelper();
                        databaseHelper.setDogInformationToDatabase(getContext(), dog_name, dog_age, dog_gender, encodedImage, breed, location, message,user_id);
                        // Exit Activity
                        getActivity().startActivity(new Intent(getContext(), ExploreActivity.class));
                        getActivity().finish();
                    }

                }
            });
        //        Creating object of DatabaseHelper for getting the id of user
        DatabaseHelper helper= new DatabaseHelper();
        helper.databaseHelper(getContext(), sessionManager.getEmail(), sessionManager.getPassword(), new VolleyCallBack() {
            @Override
            public void onSuccessResponse(List<UserGetterSetter> list) {
                for(UserGetterSetter setter:list){
                    user_id=setter.getId();
                }
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel= new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        viewModel.getSelectedItem().observe(getViewLifecycleOwner(), new Observer<List<ModalClassDog>>() {
            @Override
            public void onChanged(List<ModalClassDog> list) {
                for (ModalClassDog dog:list){
                    dog_name=dog.getDogName();
                    dog_age=dog.getDogAge();
                    dog_gender=dog.getDogGender();
                    image=dog.getDogImage();
                    // image in encoded format
                    encodeImage(image);
                }
            }
        });

    }
    // Prepare encoded image
    public void encodeImage(Bitmap bitmap){
        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] ByteArray=outputStream.toByteArray();
        encodedImage= Base64.encodeToString(ByteArray,Base64.DEFAULT);
    }

}