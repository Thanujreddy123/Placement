package com.example.placement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Recruiterlist extends AppCompatActivity {
    ListView listview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ArrayList<String>list;
    ArrayAdapter<String>adapter;
    Recruiterprofile recr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiterlist);
        recr=new Recruiterprofile();
        listview=(ListView)findViewById(R.id.lv);
        firebaseDatabase=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        reference=firebaseDatabase.getReference("Recruiter");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.item,R.id.user,list);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    recr=ds.getValue(Recruiterprofile.class);
                    list.add("  Name: "+recr.getName().toString()+"\n  Company: "+recr.getCompany()+"\n  Email: "+recr.getEmail().toString());
                }listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}