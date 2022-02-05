package com.example.placement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addrecruiter extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText company;
    private EditText name;
    private Button add;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecruiter);
        email=(EditText)findViewById(R.id.recemail);
        password=(EditText)findViewById(R.id.recpassword);
        company=(EditText)findViewById(R.id.reccompany);
        name=(EditText)findViewById(R.id.recname);
        database=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        ref=database.getReference("Recruiter");
        mAuth=FirebaseAuth.getInstance();
        add=(Button)findViewById(R.id.addrec);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailstring = email.getText().toString();
                String passstring = password.getText().toString();
                String  companystring=company.getText().toString();
                String namestring=name.getText().toString();
                Recruiterprofile rec=new Recruiterprofile(namestring,mailstring,passstring,companystring);
                ref.child(passstring).setValue(rec);
                mAuth.createUserWithEmailAndPassword(mailstring, passstring).addOnCompleteListener(Addrecruiter.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Addrecruiter.this, "sussefull", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Addrecruiter.this,Recruiterlist.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Addrecruiter.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}