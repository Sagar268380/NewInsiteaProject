package com.example.newinsiteaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnquiryForm extends AppCompatActivity {

    EditText et_name,et_email,et_subject,et_country,et_numberAdults,et_no_children,et_message;
    Button btnEnquiry;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_form);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Enquiry");

        firebaseAuth=FirebaseAuth.getInstance();

        et_subject=findViewById(R.id.et_subject);
        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_message=findViewById(R.id.et_message);
        et_numberAdults=findViewById(R.id.et_numberAdults);
        et_no_children=findViewById(R.id.et_no_children);
        et_country=findViewById(R.id.et_country);
        btnEnquiry=findViewById(R.id.btnEnquiry);

progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("SaveIng");

        btnEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                final String subject=et_subject.getText().toString();
                final String name=et_name.getText().toString();
                final String email=et_email.getText().toString();
                final String message=et_message.getText().toString();
                final String country=et_country.getText().toString();
                final String numberofchildren=et_no_children.getText().toString();
                final String numberofadults=et_numberAdults.getText().toString();


                if(subject.isEmpty()){
                    et_subject.setError("subject required");
                }
                if(name.isEmpty()){
                    et_name.setError("name required");
                }
                if(email.isEmpty()){
                    et_email.setError("email required");
                }
                if(message.isEmpty()){
                    et_message.setError("message required");
                }
                if(country.isEmpty()){
                    et_country.setError("country required");
                }
                if(numberofchildren.isEmpty()){
                    et_no_children.setError("no. of children required");
                }
                if(numberofadults.isEmpty()){
                    et_numberAdults.setError("no. of adults required");
                }
                else{
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("EnquiryDetails");
                   EnquiryModel enquiryForm=new EnquiryModel(name,email,subject,numberofchildren,numberofadults,message,country);
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(enquiryForm).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               progressDialog.dismiss();
                           }
                           else{
                               progressDialog.dismiss();
                           }
                        }
                    });

                }

            }
        });
    }
}
