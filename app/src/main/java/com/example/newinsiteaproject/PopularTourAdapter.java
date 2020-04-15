package com.example.newinsiteaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopularTourAdapter extends RecyclerView.Adapter<PopularTourAdapter.PopularTourViewholder> {
    @NonNull

    Context context;
    ArrayList<PoularTourModel> arrayList1;


    public PopularTourAdapter(Context context, ArrayList<PoularTourModel> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
    }

    @Override
    public PopularTourViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.popular_tour_card,parent,false);
        return new PopularTourViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularTourViewholder holder, int position) {
          PoularTourModel poularTourModel=arrayList1.get(position);
         holder.imageView.setImageResource(poularTourModel.image);
         holder.places.setText(poularTourModel.places);
         holder.tour1.setText(poularTourModel.tour1);
        holder.tour2.setText(poularTourModel.tour2);
        holder.tour3.setText(poularTourModel.tour3);
        holder.munnar.setText(poularTourModel.munnar);

    }

    @Override
    public int getItemCount() {
        return arrayList1.size();
    }

    public class PopularTourViewholder  extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView places,tour1,tour2,tour3,munnar;

        public PopularTourViewholder(@NonNull View itemView)
        {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageCard1);
            places=itemView.findViewById(R.id.places);
            tour1=itemView.findViewById(R.id.tour1);
            tour2=itemView.findViewById(R.id.tour2);
            tour3=itemView.findViewById(R.id.tour3);
            munnar=itemView.findViewById(R.id.munnar);
        }
    }
}
