package com.example.newinsiteaproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Itinerary extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Itinerary");

        viewPager=findViewById(R.id.viewpager3);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,2000);


    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Itinerary.this.runOnUiThread(new Runnable() {
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
