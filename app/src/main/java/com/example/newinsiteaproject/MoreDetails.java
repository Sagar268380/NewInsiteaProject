package com.example.newinsiteaproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MoreDetails extends AppCompatActivity {

   TextView cost,overview,initery;
   Button btnForm;

   ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("MoreDetails");

        overview = findViewById(R.id.overview);
        initery = findViewById(R.id.intinery);
        cost=findViewById(R.id.cost);

        initery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreDetails.this,Itinerary.class);
                startActivity(intent);
            }
        });

        btnForm=findViewById(R.id.btnForm);
        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MoreDetails.this,EnquiryForm.class);
                startActivity(intent);
            }
        });
        /*overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreDetails.this,OverView.class));
            }
        });*/

        cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreDetails.this,Cost.class));
            }
        });



        viewPager=findViewById(R.id.viewpager);
         ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);
         viewPager.setAdapter(viewPagerAdapter);

         Timer timer=new Timer();
         timer.scheduleAtFixedRate(new MyTimerTask(),2000,2000);


    }

    public class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            MoreDetails.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }
                    else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }
                    else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }
                    else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
