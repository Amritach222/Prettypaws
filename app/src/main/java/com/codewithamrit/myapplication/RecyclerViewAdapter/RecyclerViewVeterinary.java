package com.codewithamrit.myapplication.RecyclerViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithamrit.myapplication.GetterSetter.ModalVeterinary;
import com.codewithamrit.myapplication.R;

import java.util.List;

public class RecyclerViewVeterinary extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<ModalVeterinary> vet_list;

    public RecyclerViewVeterinary(Context context, List<ModalVeterinary> vet_list) {
        this.context = context;
        this.vet_list = vet_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_veterinary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModalVeterinary veterinary= vet_list.get(position);
        holder.vet_name.setText(veterinary.getVet_name());
        holder.vet_address.setText(veterinary.getVet_address());
        holder.vet_contact.setText(veterinary.getVet_contact());
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+veterinary.getVet_contact()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vet_list.size();
    }
}
class ViewHolder extends RecyclerView.ViewHolder{
    TextView vet_name,vet_address,vet_contact;
    ImageButton call;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        vet_name=itemView.findViewById(R.id.vet_name);
        vet_address=itemView.findViewById(R.id.vet_address);
        vet_contact=itemView.findViewById(R.id.vet_contact);
        call=itemView.findViewById(R.id.call_veterinary);
    }
}
