package com.example.placement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Addingmateial extends AppCompatActivity {
    EditText name;
    Button upload;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_addingmateial);
        name=(EditText)findViewById(R.id.name);
        upload=(Button) findViewById(R.id.upload);
        storageReference= FirebaseStorage.getInstance("gs://placement-b0be2.appspot.com").getReference();
        databaseReference=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/").getReference("Uploads");
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();


            }
        });

    }

    private void selectPDF() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        Toast.makeText(Addingmateial.this,"file added  ",Toast.LENGTH_LONG).show();
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            pdf(data.getData());
    }
    }

    private void pdf(Uri data) {
        StorageReference ref=storageReference.child("Uploads/"+System.currentTimeMillis()+".pdf");
        ref.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uri=taskSnapshot.getStorage().getDownloadUrl();
                    while (!uri.isComplete());
                    Uri url=uri.getResult();
                    material pdfs=new material(name.getText().toString(),url.toString());
                    databaseReference.child(databaseReference.push().getKey()).setValue(pdfs);

                    }
                });
    }
}