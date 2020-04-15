package com.example.newinsiteaproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText etEmail,etPassword,etMobile,etName;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    ImageView profileImage;
    private Uri filepath1;
    StorageReference storageReference;
    Uri profileUrl;
    String profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog=new ProgressDialog(this);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Register");

        profileImage=findViewById(R.id.UserImageProfile);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent();
                in.setType("image/*");
                in.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(in,"select image"),1);

            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Users");

        btnRegister=findViewById(R.id.btnregister);
        etEmail=findViewById(R.id.et_email);
        etMobile=findViewById(R.id.et_mobile);
        etName=findViewById(R.id.et_name);
        etPassword=findViewById(R.id.et_password);


        storageReference= FirebaseStorage.getInstance().getReference();
        progressDialog.setMessage("Loading");




        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                final String name=etName.getText().toString();
                final String mobile=etMobile.getText().toString();
                if(name.isEmpty()){
                    etName.setError("Name Required");
                }
                if (mobile.isEmpty()){
                    etMobile.setError("Mobile Required");
                }
                if (email.isEmpty()) {
                    etEmail.setError("Email Required");
                }
                if(password.isEmpty()) {
                    etPassword.setError("Password Required");
                } else {
                    progressDialog.show();
                    if (filepath1 != null)
                    {
                        try {
                            StorageReference reference = storageReference.child("Images/" + UUID.randomUUID().toString());
                            reference.putFile(filepath1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    try {
                                        Toast.makeText(RegisterActivity.this, "Profile done", Toast.LENGTH_SHORT).show();
                                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                profileUrl = uri;
                                                try {
                                                    DatabaseReference imagestore = FirebaseDatabase.getInstance().getReference().child("Image").child(firebaseAuth.getCurrentUser().getUid());
                                                    HashMap<String, String> hashMap = new HashMap<>();
                                                    hashMap.put("image", profileUrl.toString());
                                                    imagestore.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(RegisterActivity.this, "saveimage", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                } catch (Exception e) {

                                                }
                                            }
                                        });
                                    }catch (Exception e){

                                    }
                                    }
                            });
                        }catch (Exception e){

                        }
                    }
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Register Sucesdfull", Toast.LENGTH_SHORT).show();
                                SaveDataModel saveDataModel=new SaveDataModel(mobile,name,password,email);
                                databaseReference.child("PersonalDetails").child(firebaseAuth.getCurrentUser().getUid()).setValue(saveDataModel);
                                Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Bitmap bitmap;
            filepath1 = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath1);
               profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
