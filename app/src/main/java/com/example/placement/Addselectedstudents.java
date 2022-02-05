package com.example.placement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addselectedstudents extends AppCompatActivity {
    private Button btn;
    private EditText name;
    private EditText rollno;
    private EditText company;
    private EditText salary;
    private FirebaseDatabase data;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addselectedstudents);
        name=(EditText)findViewById(R.id.name);
        rollno=(EditText)findViewById(R.id.rollno);
        company=(EditText)findViewById(R.id.company);
        salary=(EditText)findViewById(R.id.salary);
        btn=(Button)findViewById(R.id.btnadd);
        data=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        ref=data.getReference("Selectedstudents");
       btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namestring = name.getText().toString();
                String rollnostring = rollno.getText().toString();
                String companystring = company.getText().toString();
                String salarystring = salary.getText().toString();
                selectedprofile student=new selectedprofile(namestring,rollnostring,companystring,salarystring);
                ref.child(rollnostring).setValue(student);
                Toast.makeText(Addselectedstudents.this,"sucess",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Addselectedstudents.this,Selectedstudentslist.class));
            }
        });
    }
}