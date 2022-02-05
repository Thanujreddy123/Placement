package com.example.placement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminmodule extends AppCompatActivity {
    private Button student;
    private Button recruiter;
    private Button viewstudent;
    private Button viewrecruiter;
    private Button logout;
    private Button drives;
    private Button selectedstudents;
    private Button addmaterial;
    private Button viewmaterial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmodule);
        student=(Button) findViewById(R.id.addst);
        viewmaterial=(Button)findViewById(R.id.viewmaterials);
        addmaterial=(Button)findViewById(R.id.addmaterial);
        drives=(Button)findViewById(R.id.drives);
        recruiter=(Button) findViewById(R.id.addrecruiter);
        viewrecruiter=(Button)findViewById(R.id.viewrecruiters);
        selectedstudents=(Button)findViewById(R.id.selectedstudents);
        viewstudent = (Button)findViewById(R.id.viewstudents);
        logout=(Button)findViewById(R.id.logout);
        viewmaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminmodule.this,Allmaterials.class);
                startActivity(intent);
            }
        });
        addmaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminmodule.this,Addingmateial.class));
            }
        });
        selectedstudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminmodule.this,Selectedstudentslist.class));
            }
        });
        drives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminmodule.this,Drives.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminmodule.this,MainActivity.class);
                startActivity(intent);
            }
        });
        viewstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminmodule.this,Studentlist.class);
                startActivity(intent);
            }
        });
        viewrecruiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminmodule.this,Recruiterlist.class);
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminmodule.this,Addstudent.class);
                startActivity(intent);
            }
        });
        recruiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Adminmodule.this,Addrecruiter.class);
                startActivity(intent);
            }
        });
    }
}