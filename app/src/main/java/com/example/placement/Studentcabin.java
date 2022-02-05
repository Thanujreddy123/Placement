package com.example.placement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Studentcabin extends AppCompatActivity {
    private TextView name;
    private TextView branch;
    private TextView email;
    private TextView percentage;
    private TextView placed;
    private Button logout;
    private Button upcomingdrives;
    private Button selectedstudents;
    private Button material;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentcabin);
        logout=(Button)findViewById(R.id.logout);
        material=(Button)findViewById(R.id.materials);
        selectedstudents=(Button)findViewById(R.id.selectedstudents);
        upcomingdrives=(Button)findViewById(R.id.upcomingdrives);
        name=(TextView)findViewById(R.id.name);
        branch=(TextView)findViewById(R.id.branch);
        percentage=(TextView)findViewById(R.id.percentage);
        placed=(TextView)findViewById(R.id.placed);
        email=(TextView)findViewById(R.id.email);
        material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Studentcabin.this,Allmaterials.class);
                startActivity(intent);
            }
        });
        selectedstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Studentcabin.this,Tpomail.class));
            }
        });
        upcomingdrives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Studentcabin.this,Driveslist.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Studentcabin.this,MainActivity.class));
            }
        });
        Intent intent=getIntent();
        String username=intent.getStringExtra("name");
        String userbranch=intent.getStringExtra("branch");
        String useremail=intent.getStringExtra("email");
        String userpercentage=intent.getStringExtra("percentage");
        String userplaced=intent.getStringExtra("placed");
        name.setText("Name:"+username);
        branch.setText("Branch:"+userbranch);
        percentage.setText("Percentage:"+userpercentage);
        email.setText("Email:"+useremail);
        placed.setText("Rollno:"+userplaced);
    }
}