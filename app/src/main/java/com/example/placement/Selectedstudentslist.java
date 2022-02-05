package com.example.placement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Selectedstudentslist extends AppCompatActivity {
    ListView lvw;
    FirebaseDatabase data;
    DatabaseReference refe;
    ArrayList<String>lists;
    ArrayAdapter<String>adapters;
    selectedprofile stp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedstudentslist);
        lvw=(ListView)findViewById(R.id.lst);
        stp=new selectedprofile();
        data=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        refe=data.getReference("Selectedstudents");
        lists=new ArrayList<>();
        adapters=new ArrayAdapter<String>(this,R.layout.item,R.id.user,lists);
        refe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    stp=ds.getValue(selectedprofile.class);
                    lists.add("  Name: "+stp.getName().toString()+"\n  Company: "+stp.getCompany()+"\n  rollno: "+stp.getRollno().toString()+"\n  Salary: "+stp.getSalary());
                }lvw.setAdapter(adapters);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}