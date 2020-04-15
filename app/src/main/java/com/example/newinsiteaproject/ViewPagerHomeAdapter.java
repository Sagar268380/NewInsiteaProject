package com.example.newinsiteaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerHomeAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    Integer[] integers={R.drawable.tour1,R.drawable.tour2,R.drawable.tour5,R.drawable.tour4};

    public ViewPagerHomeAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return integers.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
       layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View view=layoutInflater.inflate(R.layout.home_item,null);
        ImageView imageView=view.findViewById(R.id.item_movie_img);
        imageView.setImageResource(integers[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0){
                    Toast.makeText(context, "Slide1", Toast.LENGTH_SHORT).show();
                }
                if(position==1){
                    Toast.makeText(context, "Slide2", Toast.LENGTH_SHORT).show();
                }if(position==2){
                    Toast.makeText(context, "Slide3", Toast.LENGTH_SHORT).show();
                }if(position==3){
                    Toast.makeText(context, "Slide4", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewPager viewPager=(ViewPager)container;
        viewPager.addView(view,0);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager) container;
        View view=(View) object;
        viewPager.removeView(view);
    }
}
