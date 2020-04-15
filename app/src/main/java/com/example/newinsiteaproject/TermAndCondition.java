package com.example.newinsiteaproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TermAndCondition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_and_condition);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("TermAndCondition");

    }
}
