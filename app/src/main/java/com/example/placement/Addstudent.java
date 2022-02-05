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

public class Addstudent extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText placed;
    private EditText branch;
    private EditText percentage;
    private EditText name;
    private Button add;
    private FirebaseAuth mAuth;
    private FirebaseDatabase data;
    private DatabaseReference refer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        email=(EditText)findViewById(R.id.studentemail);
        password=(EditText)findViewById(R.id.studentpassword);
        placed=(EditText)findViewById(R.id.Placed);
        branch=(EditText)findViewById(R.id.studentbranch);
        percentage=(EditText)findViewById(R.id.studentpercentage);
        name=(EditText)findViewById(R.id.studentname);
        mAuth=FirebaseAuth.getInstance();
        data=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        refer=data.getReference("Students");
        add=(Button)findViewById(R.id.addst);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailstring = email.getText().toString();
                String passstring = password.getText().toString();
                String placedstring = placed.getText().toString();
                String percentagestring = percentage.getText().toString();
                String branchstring = branch.getText().toString();
                String namestring = name.getText().toString();
                Studentprofile student=new Studentprofile(namestring,passstring,mailstring,percentagestring,branchstring,placedstring);
                refer.child(passstring).setValue(student);
                mAuth.createUserWithEmailAndPassword(mailstring, passstring).addOnCompleteListener(Addstudent.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Addstudent.this, "sussefull", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Addstudent.this,Studentlist.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Addstudent.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}