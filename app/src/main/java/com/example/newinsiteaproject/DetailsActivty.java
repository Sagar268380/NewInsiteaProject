package com.example.newinsiteaproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivty extends AppCompatActivity {

    TextView placeNameDt;
    ImageView imageView;

    String placeName;
    Integer imagePlace;

    Button btnMore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_activty);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Details");

        placeNameDt=findViewById(R.id.placeNameDt);
        imageView=findViewById(R.id.imageView);
        btnMore=findViewById(R.id.btnMore);

        Intent intent=getIntent();
        placeName=intent.getStringExtra("placename");
        placeNameDt.setText(placeName);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            int resId=bundle.getInt("image");
            imageView.setImageResource(resId);
        }

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(DetailsActivty.this,MoreDetails.class);
                startActivity(intent1);
            }
        });
    }
}
