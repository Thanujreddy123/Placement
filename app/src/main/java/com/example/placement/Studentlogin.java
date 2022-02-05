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

public class Studentlogin extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
        mAuth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.loginstudentemail);
        password=(EditText)findViewById(R.id.loginstudentpassword);
        btn=(Button)findViewById(R.id.loginstudent);
        database=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        reference=database.getReference("Students");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    final String mailstring = email.getText().toString();
                    final String mailpassword = password.getText().toString();
                    mAuth.signInWithEmailAndPassword(mailstring, mailpassword).addOnCompleteListener(Studentlogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Toast.makeText(Studentlogin.this, "successful", Toast.LENGTH_SHORT).show();
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String name = dataSnapshot.child(mailpassword).child("name").getValue(String.class);
                                        String branch = dataSnapshot.child(mailpassword).child("branch").getValue(String.class);
                                        String email = dataSnapshot.child(mailpassword).child("email").getValue(String.class);
                                        String placed = dataSnapshot.child(mailpassword).child("placed").getValue(String.class);
                                        String percentage = dataSnapshot.child(mailpassword).child("percentage").getValue(String.class);

                                        Intent intent = new Intent(Studentlogin.this, Studentcabin.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("branch", branch);
                                        intent.putExtra("email", email);
                                        intent.putExtra("placed", placed);
                                        intent.putExtra("percentage", percentage);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            } else {
                                Toast.makeText(Studentlogin.this, "wrong credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Studentlogin.this,"enter all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}