package com.example.newinsiteaproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<RecycleViewAdapterModelTwo> arrayList;

    public RecyclerViewAdapter(Context context, ArrayList<RecycleViewAdapterModelTwo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.hoe_page_recycleview1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecycleViewAdapterModelTwo recycleViewAdapterModelTwo=arrayList.get(position);

        holder.price.setText(recycleViewAdapterModelTwo.getPrice());
        holder.hotelName.setText(recycleViewAdapterModelTwo.getHotelName());
        holder.city.setText(recycleViewAdapterModelTwo.getCity());
        holder.hotelImage.setImageResource(recycleViewAdapterModelTwo.hotelImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

TextView city,hotelName,price;
ImageView hotelImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city=itemView.findViewById(R.id.hotelCity);
            hotelName=itemView.findViewById(R.id.hotelName);
            hotelImage=itemView.findViewById(R.id.hotelImage);
            price=itemView.findViewById(R.id.hotelPrice);
        }
    }
}