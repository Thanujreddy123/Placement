package com.example.placement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.PriorityQueue;

public class Recruitercabin extends AppCompatActivity {
    private TextView company;
    private TextView name;
    private TextView email;
    private Button logout;
    private Button viewstudent;
    private Button addselected;
    private Button viewselected;
    private Button tpo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitercabin);
        logout=(Button)findViewById(R.id.logout);
        viewselected=(Button)findViewById(R.id.viewselectedstudents);
        tpo=(Button )findViewById(R.id.college);
        company=(TextView) findViewById(R.id.company);
        name=(TextView) findViewById(R.id.name);
        email=(TextView) findViewById(R.id.email);
        viewstudent=(Button) findViewById(R.id.viewstudents);
        addselected=(Button)findViewById(R.id.addselectedstudents);
        viewselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recruitercabin.this,Selectedstudentslist.class));
            }
        });
        tpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recruitercabin.this,Tpomail.class));
            }
        });
        addselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recruitercabin.this,Addselectedstudents.class));
            }
        });
        viewstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recruitercabin.this,Studentlist.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Recruitercabin.this,MainActivity.class));
            }
        });
        Intent intent=getIntent();
        String username=intent.getStringExtra("name");
        String usercompany=intent.getStringExtra("company");
        String useremail=intent.getStringExtra("email");
        name.setText("Name : "+username);
        email.setText("Email : "+useremail);
        company.setText("company : "+usercompany);
    }
}