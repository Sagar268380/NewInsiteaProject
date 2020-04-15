package com.example.newinsiteaproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageAdapterViewholder> {

   Context context;
   ArrayList<AdapterModel> arrayList;


    public ImageAdapter(Context context, ArrayList<AdapterModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ImageAdapterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_list,parent,false);
        return new ImageAdapterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterViewholder holder, int position) {
        AdapterModel adapterModel=arrayList.get(position);
        holder.imageView.setImageResource(adapterModel.imageid);
        holder.price.setText(adapterModel.getPrice());
        holder.placeName.setText(adapterModel.getPlaceName());
        holder.days.setText(adapterModel.getDays());

        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                String price=arrayList.get(position).getPrice();
                String placeName=arrayList.get(position).getPlaceName();
                String days=arrayList.get(position).getDays();
                Integer image=arrayList.get(position).getImageid();

                Intent intent=new Intent(context,DetailsActivty.class);
                intent.putExtra("price",price);
                intent.putExtra("placename",placeName);
                intent.putExtra("days",days);
                intent.putExtra("image",image);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ImageAdapterViewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ItemClickListner itemClickListner;

        RoundedImageView imageView;
        TextView price,placeName,days;
        public ImageAdapterViewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageCard);
            placeName=itemView.findViewById(R.id.placeName);
            price=itemView.findViewById(R.id.price);
            days=itemView.findViewById(R.id.days);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            this.itemClickListner.onItemClickListner(view,getLayoutPosition());
        }

        public void setItemClickListner(ItemClickListner ic){
            this.itemClickListner=ic;
        }
    }
}
