package com.example.placement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private int counter=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        name=(EditText)findViewById(R.id.nameid);
        password=(EditText)findViewById(R.id.passwordid);
        info=(TextView)findViewById(R.id.attemptsleft);
        login=(Button)findViewById(R.id.loginid);
        info.setText("No of attempts remaining: 5");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                validate(name.getText().toString(),password.getText().toString());
                }
                else{
                    Toast.makeText(Adminlogin.this,"enter all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void validate(String username,String userpassword){
        if((username.equals("Tpo")) && (userpassword.equals("123456789"))){
            Toast.makeText(Adminlogin.this," logged in",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Adminlogin.this,Adminmodule.class);
            startActivity(intent);
            finish();
        }
        else{
            counter--;
            info.setText("No of attempts remaining:"+String.valueOf(counter));
            if(counter==0){
                login.setEnabled(false);
            }
        }
    }
}