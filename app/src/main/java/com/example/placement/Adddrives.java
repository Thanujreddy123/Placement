package com.example.placement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Adddrives extends AppCompatActivity {
    private EditText company;
    private EditText jobrole;
    private EditText ctc;
    private EditText date;
    private EditText percentage;
    private Button drive;
    private FirebaseDatabase dat;
    private DatabaseReference re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddrives);
        company=(EditText)findViewById(R.id.companyname);
        jobrole=(EditText)findViewById(R.id.Jobrole);
        ctc=(EditText)findViewById(R.id.ctc);
        date=(EditText)findViewById(R.id.date);
        percentage=(EditText)findViewById(R.id.percentage);
        drive=(Button)findViewById(R.id.adddrives);
        dat=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        re=dat.getReference("Upcomingdrives");
        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companystring=company.getText().toString();
                String jobstring=jobrole.getText().toString();
                String ctcstring=ctc.getText().toString();
                String datestring=date.getText().toString();
                String percentstring=percentage.getText().toString();
                Drivesprofiles dri=new Drivesprofiles(companystring,datestring,ctcstring,jobstring,percentstring);
                re.child(companystring).setValue(dri);
                Toast.makeText(Adddrives.this,"sucessfully added",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Adddrives.this,Driveslist.class);
                startActivity(intent);
            }
        });
    }
}