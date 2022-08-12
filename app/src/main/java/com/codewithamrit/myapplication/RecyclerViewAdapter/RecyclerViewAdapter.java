package com.codewithamrit.myapplication.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.codewithamrit.myapplication.GetterSetter.ModalClassDog;
import com.codewithamrit.myapplication.HandleDatabase.CallBackCheckLike;
import com.codewithamrit.myapplication.HandleDatabase.HandleLikeDislike;
import com.codewithamrit.myapplication.NetworkIP.NetworkIP;
import com.codewithamrit.myapplication.PreviewDogActivity;
import com.codewithamrit.myapplication.R;
import com.codewithamrit.myapplication.SignInActivity;
import com.codewithamrit.myapplication.session.SessionManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

private Context context;
private List<ModalClassDog> doginfolist;

    public RecyclerViewAdapter(Context context, List<ModalClassDog> doginfolist) {
        this.context = context;
        this.doginfolist = doginfolist;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ModalClassDog classDog=doginfolist.get(position);
        viewHolder.name.setText(classDog.getDogName());
        viewHolder.age.setText(classDog.getDogAge());
        viewHolder.gender.setText(classDog.getDogGender());
        viewHolder.address.setText(classDog.getDogLocation());
        Picasso.get().load(classDog.getImageUrl()).into(viewHolder.image);
        viewHolder.name.setText(classDog.getDogName());
        // Set Onclick listener
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFullActivityLayout();
            }
        });
        // getting like button status
        viewHolder.getLikeButtonStatus(context,classDog.getId(),new SessionManager(context).getUserId());
//        set OnclickListener on like button



            viewHolder.like_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (new SessionManager(context).getLogin() == true) {
                        viewHolder.testclick = true;
                        if (viewHolder.testclick == true) {
                            new HandleLikeDislike().checkLikeStatus(context, new SessionManager(context).getUserId(), classDog.getId(), new CallBackCheckLike() {
                                @Override
                                public void onSuccessResponse(boolean checklike) {
                                    if (checklike == true) {
                                        new HandleLikeDislike().deleteLike(context, new SessionManager(context).getUserId(), classDog.getId(), new CallBackCheckLike() {
                                            @Override
                                            public void onSuccessResponse(boolean checklike) {
                                                if (checklike == true) {
                                                    viewHolder.like_button.setImageResource(R.drawable.ic_favwhite);
                                                    viewHolder.testclick = false;
                                                }
                                            }
                                        });
                                    } else {
                                        new HandleLikeDislike().insertLike(context, new SessionManager(context).getUserId(), classDog.getId(), new CallBackCheckLike() {
                                            @Override
                                            public void onSuccessResponse(boolean checklike) {
                                                if (checklike == true) {
                                                    viewHolder.like_button.setImageResource(R.drawable.ic_fav);
                                                    viewHolder.testclick = false;

                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                    else{
                        context.startActivity(new Intent(context, SignInActivity.class));
                        Toast.makeText(context, "SignIn First", Toast.LENGTH_SHORT).show();
                    }
                }
            });


//        open dogpreview activity
        viewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> arrayList= new ArrayList<String>();
                arrayList.add(0,classDog.getDogName());
                arrayList.add(1,classDog.getDogAge());
                arrayList.add(2,classDog.getDogGender());
                arrayList.add(3,classDog.getDogLocation());
                arrayList.add(4,classDog.getImageUrl());
                arrayList.add(5,classDog.getDogMessage());
                arrayList.add(6,classDog.getId());
                Intent intent= new Intent(context, PreviewDogActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT,arrayList);
                context.startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return doginfolist.size();
    }
    // ViewHolder class
public static class ViewHolder extends RecyclerView.ViewHolder {
        private String url="http://"+ NetworkIP.NETWORK_URL +"/dogFinderApp/checklike.php";
        private RelativeLayout container;
        private TextView name,age,gender,address;
        private ImageView image;
        private ImageButton like_button;
        private boolean testclick=false;

        public ViewHolder(View view) {
        super(view);
        container=view.findViewById(R.id.container);
        name=view.findViewById(R.id.row_dog_name);
        age=view.findViewById(R.id.row_dog_age);
        gender=view.findViewById(R.id.row_dog_gender);
        address=view.findViewById(R.id.row_dog_location);
        image=view.findViewById(R.id.row_dog_image);
        like_button=view.findViewById(R.id.dog_like_btn);
        // Define click listener for the ViewHolder's View
    }
    //Check likebutton status
    public void getLikeButtonStatus(Context context,String dogId,String userId){
            if (new SessionManager(context).getLogin()==true){


        new HandleLikeDislike().checkLikeStatus(context, userId, dogId, new CallBackCheckLike() {
            @Override
            public void onSuccessResponse(boolean checklike) {
                if(checklike==true){
                    like_button.setImageResource(R.drawable.ic_fav);
                }
                else{
                    like_button.setImageResource(R.drawable.ic_favwhite);
                }
            }
        });
            }
    }

}
    private void openFullActivityLayout() {
        // Sending message to another activity
    }
}
