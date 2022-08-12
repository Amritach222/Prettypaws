package com.codewithamrit.myapplication.NavigationButtonFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.RecyclerViewAdapter.UpdateRecyclerView;
import com.codewithamrit.myapplication.session.SessionManager;

import java.util.List;


public class UpdateFragments extends Fragment {
    public static List<String> updates=null;
    RecyclerView recyclerView;
    TextView no_update;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update_fragments, container, false);
        //Call from here
        no_update=view.findViewById(R.id.no_updates);
        recyclerView=view.findViewById(R.id.recyclerview_for_updates);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if( new SessionManager(getContext()).getLogin()==true){
            //        Checking updates
            if (updates==null){
                no_update.setText("No updates found!");
            }
            else{
                UpdateRecyclerView updateRecyclerView= new UpdateRecyclerView(getContext(),updates);
                recyclerView.setAdapter(updateRecyclerView);
            }

        }
        else{
            no_update.setText("No updates found!");
        }


        return view;
    }
//    String message="This is new notification from PrettyPaws Developer team.";
//    NotificationCompat.Builder builder= new NotificationCompat.Builder(getContext())
//            .setSmallIcon(R.drawable.ic_message)
//            .setContentTitle("New Notification")
//            .setContentText(message)
//            .setAutoCancel(true);
//    Intent intent=new Intent(getContext(),UpdateFragments.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("message",message);
//    PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//                builder.setContentIntent(pendingIntent);
//    NotificationManager notificationManager=(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//                notificationManager.notify(0,builder.build());
}