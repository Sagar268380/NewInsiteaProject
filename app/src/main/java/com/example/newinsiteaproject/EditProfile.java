package com.example.newinsiteaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {


    Button btnRegister;
    EditText etEmail,etPassword,etMobile,etName;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");

        btnRegister=findViewById(R.id.btnregister);
        etEmail=findViewById(R.id.et_email);
        etMobile=findViewById(R.id.et_mobile);
        etName=findViewById(R.id.et_name);
        etPassword=findViewById(R.id.et_password);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String email=dataSnapshot.child("PersonalDetails").child(firebaseAuth.getCurrentUser().getUid()).child("email").getValue().toString();
                final String mobile=dataSnapshot.child("PersonalDetails").child(firebaseAuth.getCurrentUser().getUid()).child("mobile").getValue().toString();
                final String name=dataSnapshot.child("PersonalDetails").child(firebaseAuth.getCurrentUser().getUid()).child("name").getValue().toString();
                final String password=dataSnapshot.child("PersonalDetails").child(firebaseAuth.getCurrentUser().getUid()).child("password").getValue().toString();

                etEmail.setText(email);
                etName.setText(name);
                etMobile.setText(mobile);
                etPassword.setText(password);
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email1 = etEmail.getText().toString();
                        String password1 = etPassword.getText().toString();
                        String name1=etName.getText().toString();
                         String mobile1=etMobile.getText().toString();
                        SaveDataModel saveDataModel=new SaveDataModel(mobile1,name1,password1,email1);
                        databaseReference.child("PersonalDetails").child(firebaseAuth.getCurrentUser().getUid()).setValue(saveDataModel);
                        Toast.makeText(EditProfile.this, "click", Toast.LENGTH_SHORT).show();
                    }
                });
                //etPassword.setText(password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
