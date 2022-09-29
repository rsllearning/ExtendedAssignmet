package com.example.extendedassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    List<Contact> contactList=new ArrayList<>();
    Context context;
    ItemClick itemClick;

    public RVAdapter(Context context, ItemClick itemClick) {
        this.context = context;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        ViewHolder vh=new ViewHolder(itemView);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onItemClick(contactList.get(vh.getAdapterPosition()));
            }
        });
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact=contactList.get(position);
        holder.name.setText(contact.getName().toString());
        holder.phone.setText(contact.getPhoneNo().toString());
        holder.email.setText(contact.getEmail().toString());

    }
    // updating list , everytime when data inside get change
    void updateList(List<Contact> newList){
        contactList.clear();
       contactList.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            phone=itemView.findViewById(R.id.tvNumber);
            email=itemView.findViewById(R.id.tvEmail);
        }
    }
}

// Interface for item clickListener
interface ItemClick{
    void onItemClick(Contact contact);
}
