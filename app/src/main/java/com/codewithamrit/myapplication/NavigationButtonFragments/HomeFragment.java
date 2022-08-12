package com.codewithamrit.myapplication.NavigationButtonFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithamrit.myapplication.GetterSetter.ModalClassDog;
import com.codewithamrit.myapplication.HandleDatabase.DogInformationDatabaseHelper;
import com.codewithamrit.myapplication.HandleDatabase.VolleyCallBackForDog;
import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.RecyclerViewAdapter.RecyclerViewAdapter;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        // call from here
        recyclerView=view.findViewById(R.id.recyclerview_for_dog);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       // Setup Adapter
        sentDataToAdapter();
        return view;
    }

    private void sentDataToAdapter() {
        DogInformationDatabaseHelper databaseHelper= new DogInformationDatabaseHelper();
        databaseHelper.retrieveDogInformation(getContext(), new VolleyCallBackForDog() {
            @Override
            public void onSuccessResponse(ArrayList<ModalClassDog> list) {

                    adapter= new RecyclerViewAdapter(getContext(),list);
                    recyclerView.setAdapter(adapter);
            }
        });
    }
}