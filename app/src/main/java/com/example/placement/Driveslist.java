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

public class Driveslist extends AppCompatActivity {
    ListView listview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Drivesprofiles drive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driveslist);
        setContentView(R.layout.activity_studentlist);
        listview=(ListView)findViewById(R.id.listv);
        drive=new Drivesprofiles();
        firebaseDatabase=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        reference=firebaseDatabase.getReference("Upcomingdrives");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.item,R.id.user,list);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    drive=ds.getValue(Drivesprofiles.class);
                    list.add("  Company: "+drive.getCompany().toString()+"\n  ctc: "+drive.getCtc()+"\n  Date: "+drive.getDate().toString()+"\n  Percentage: "+drive.getPercentage().toString()+"\n  Job role: "+drive.getRole().toString());
                }listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}