package com.codewithamrit.myapplication.NavigationButtonFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithamrit.myapplication.GetterSetter.ModalClassDog;
import com.codewithamrit.myapplication.HandleDatabase.FavouriteDatabase;
import com.codewithamrit.myapplication.HandleDatabase.VolleyCallBackForDog;
import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.RecyclerViewAdapter.RecyclerViewAdapter;
import com.codewithamrit.myapplication.session.SessionManager;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    private TextView noDataText;
    private ImageView noDataImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourite, container, false);
        noDataImage=view.findViewById(R.id.nodataImage);
        noDataText=view.findViewById(R.id.noDataText);
        //Call from Here
        recyclerView=view.findViewById(R.id.recyclerview_for_favourite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Setup Adapter
        if(new SessionManager(getContext()).getLogin()==true){
            sentDataToAdapter();
        }
        else{
            noDataImage.setImageResource(R.drawable.nodata);
            noDataText.setText("No favourites found");
        }
        return  view;
    }

    private void sentDataToAdapter() {

        new FavouriteDatabase().favouriteDogInformation(getContext(), new SessionManager(getContext()).getUserId(), new VolleyCallBackForDog() {
            @Override
            public void onSuccessResponse(ArrayList<ModalClassDog> list) {

                if(list.size()==0){
                    noDataImage.setImageResource(R.drawable.nodata);
                    noDataText.setText("No favourites found");
                }
                else{
                    adapter= new RecyclerViewAdapter(getContext(),list);
                    recyclerView.setAdapter(adapter);
                }

            }
        });
    }
}