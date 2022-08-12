package com.codewithamrit.myapplication.AddDogFragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.codewithamrit.myapplication.CompressImage.ImageCompression;
import com.codewithamrit.myapplication.GetterSetter.ModalClassDog;
import com.codewithamrit.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class AddDogFragmentSecond  extends Fragment implements View.OnClickListener {
    //global variables
    Button selectImage;
    public static final int PICK_IMAGE_REQUEST=100;
    private Uri imagePath;
    private ImageView imageView;
    private Bitmap imageToStore, image;
    RadioButton button;
    private EditText dog_name, dog_age;
    private FloatingActionButton floatingActionButton;
    private ItemViewModel viewModel;
    ModalClassDog classDog;
    public AddDogFragmentSecond(){

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_dog_second,container,false);
        //Assigning id
        view.findViewById(R.id.radio_male).setOnClickListener(this);
        view.findViewById(R.id.radio_female).setOnClickListener(this);
        selectImage=view.findViewById(R.id.button_for_picture);
        imageView=view.findViewById(R.id.dog_image);
        dog_name=view.findViewById(R.id.dog_name);
        dog_age=view.findViewById(R.id.dog_age);
        floatingActionButton=view.findViewById(R.id.fab2);
        // Creating Object of ModalClassDog
        classDog= new ModalClassDog();
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent,PICK_IMAGE_REQUEST);
                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    // Sending data to the third fragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try{
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                imagePath=data.getData();
                storeImage();
            }
        }
        catch(Exception e){
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    public Bitmap storeImage() throws IOException {
        try{
            String path=imagePath.getPath();
            File file= new File(path);
            String imageName=file.getName();
            Log.d("amrit", "onActivityResult: "+imageName);
//          getting Image using mediaStore to load image from image path.
            Bitmap fillSizeImage= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imagePath);
            //   ScaleDown Image
            imageToStore=ImageCompression.resizeImage(fillSizeImage,240000);
            imageView.setImageBitmap(imageToStore);
        }catch (IOException e){
            Toast.makeText(getContext(), "Please Select an image first", Toast.LENGTH_SHORT).show();
        }
        return imageToStore;
    }

    @Override
    public void onClick(View view) {
        button=view.findViewById(view.getId());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final List<ModalClassDog>dog_info_list= new ArrayList<>();
        final ItemViewModel viewModel= new ViewModelProvider(requireActivity()).get(ItemViewModel.class);
        //      set onCliclickListeber in floating action button
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= dog_name.getText().toString().trim();
                String age=dog_age.getText().toString().trim();
                String gender=button.getText().toString().trim();
                try {
                     image=storeImage();
                    classDog.setDogImage(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(gender.isEmpty() || imageToStore.getByteCount()==0)
                {
                    Toast.makeText(getContext(), "Please kindly fill all * fields", Toast.LENGTH_SHORT).show();
                }else {


                    classDog.setDogName(name);
                    classDog.setDogAge(age);
                    classDog.setDogGender(gender);
                    dog_info_list.add(classDog);
                    viewModel.setData(dog_info_list);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container, AddDogFragmentThird.class, null)
                            .commit();
                }
            }
        });
    }
}
