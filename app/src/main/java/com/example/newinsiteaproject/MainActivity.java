package com.example.newinsiteaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText etEmail,etPassword;
    TextView txtRegister;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);

        txtRegister=findViewById(R.id.txtRegister);
        btnLogin=findViewById(R.id.btnLogin);
        etEmail=findViewById(R.id.et_email);
        etPassword=findViewById(R.id.et_password);
        firebaseAuth=FirebaseAuth.getInstance();

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Login");

        progressDialog.setMessage("Loading");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();
                if (email.isEmpty()) {
                    etEmail.setError("Email Required");
                }
                 if (password.isEmpty()) {
                    etPassword.setError("Password Required");
                }else {
                     progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(MainActivity.this, "Login Sucessfully", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }
}
