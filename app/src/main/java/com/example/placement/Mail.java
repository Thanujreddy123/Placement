package com.example.placement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mail extends AppCompatActivity {
    private EditText to;
    private EditText subject;
    private EditText body;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        to=(EditText)findViewById(R.id.to);
        subject=(EditText)findViewById(R.id.subject);
        body=(EditText)findViewById(R.id.body);
        Intent intent=getIntent();
        String username=intent.getStringExtra("name");
        to.setText(username);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!to.getText().toString().isEmpty() && !subject.getText().toString().isEmpty() &&!body.getText().toString().isEmpty()){
                    Intent intent=new Intent(Intent.ACTION_SENDTO);
                    intent.putExtra(Intent.EXTRA_EMAIL,new String[]{to.getText().toString()});
                    intent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT,body.getText().toString());
                    intent.setData(Uri.parse("mailto:"));
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(Mail.this,"enter all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}