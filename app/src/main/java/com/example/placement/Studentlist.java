package com.example.placement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Studentlist extends AppCompatActivity {
    ListView listview;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ArrayList<String> list;
    ArrayList<String> maillist;
    ArrayAdapter<String> adapter;
    Studentprofile student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);
        listview=(ListView)findViewById(R.id.listv);
        student=new Studentprofile();
        firebaseDatabase=FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/");
        reference=firebaseDatabase.getReference("Students");
        list=new ArrayList<>();
        maillist=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.item,R.id.user,list);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    student=ds.getValue(Studentprofile.class);
                    list.add("  Name: "+student.getName().toString()+"\n  Rollno: "+student.getPlaced()+"\n  Email: "+student.getEmail().toString()+"\n  Percentage: "+student.getPercentage().toString()+"\n  Branch: "+student.getBranch().toString());
                    maillist.add(student.getEmail().toString());
                }listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Studentlist.this,Mail.class);
                String s=maillist.get(position).toString();
                intent.putExtra("name",s);
                startActivity(intent);
            }
        });
    }
}