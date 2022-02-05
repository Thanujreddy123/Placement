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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Recruiterlogin extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText name;
    private EditText company;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private Button btn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiterlogin);
        mAuth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.loginrecemail);
        password=(EditText)findViewById(R.id.loginrecpassword);
        btn=(Button)findViewById(R.id.loginrec);
        database=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        ref=database.getReference("Recruiter");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    String mailstring = email.getText().toString();
                    final String mailpassword = password.getText().toString();
                    mAuth.signInWithEmailAndPassword(mailstring, mailpassword).addOnCompleteListener(Recruiterlogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Recruiterlogin.this, "successful", Toast.LENGTH_SHORT).show();
                                ref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String name = dataSnapshot.child(mailpassword).child("name").getValue(String.class);
                                        String company = dataSnapshot.child(mailpassword).child("company").getValue(String.class);
                                        String email = dataSnapshot.child(mailpassword).child("email").getValue(String.class);
                                        Intent intent = new Intent(Recruiterlogin.this, Recruitercabin.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("email", email);
                                        intent.putExtra("company", company);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            } else {
                                Toast.makeText(Recruiterlogin.this, "wrong credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Recruiterlogin.this,"enter all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}