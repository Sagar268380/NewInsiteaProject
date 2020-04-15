package com.example.newinsiteaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {


    TextView etEmail,etMobile,etName;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference,databaseReference1;

    ImageView profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Profile");

        etEmail=findViewById(R.id.et_email);
        etMobile=findViewById(R.id.et_mobile);
        etName=findViewById(R.id.et_name);
        profileImage=findViewById(R.id.UserImageProfile);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Image").child(firebaseAuth.getCurrentUser().getUid()).child("image");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String link=dataSnapshot.getValue().toString();
                Log.d("image","image"+link);
                Picasso.get().load(link).into(profileImage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference1=FirebaseDatabase.getInstance().getReference().child("Users").child("PersonalDetails");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).child("name").getValue().toString();
                String mobile=dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).child("mobile").getValue().toString();
                String email=dataSnapshot.child(firebaseAuth.getCurrentUser().getUid()).child("email").getValue().toString();
                etMobile.setText(mobile);
                etName.setText(name);
                etEmail.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
