package com.codewithamrit.myapplication.RecyclerViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.codewithamrit.myapplication.R;

import java.util.List;

public class UpdateRecyclerView extends RecyclerView.Adapter<UpdateRecyclerView.ViewHolder>{
    Context context;
    List<String> updates;

    public UpdateRecyclerView(Context context, List<String> updates) {
        this.context = context;
        this.updates = updates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_update, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String msg=updates.get(position).toString();
        holder.message.setText(msg);
//        Set OnclickListener
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMessage();
            }
        });

    }

    @Override
    public int getItemCount() {
        return updates.size();
    }
    public static class ViewHolder extends RecyclerViewAdapter.ViewHolder{
        TextView message;
        LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            message=view.findViewById(R.id.text_message);
            layout=view.findViewById(R.id.bg_update);
        }
    }
    public void openMessage(){
        Toast.makeText(context, "Thank You!", Toast.LENGTH_SHORT).show();
    }
}
