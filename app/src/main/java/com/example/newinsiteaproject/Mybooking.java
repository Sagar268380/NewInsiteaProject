package com.example.newinsiteaproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Mybooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("MyBooking");

    }
}
