package com.example.placement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Allmaterials extends AppCompatActivity {
    ListView mypdflist;
    DatabaseReference databaseReference;
    List<material> uploading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allmaterials);
        mypdflist=(ListView)findViewById(R.id.pdfs);
        uploading=new ArrayList<>();
        viewAllFiles();
        mypdflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                material pdfs=uploading.get(position);
                Intent intent=new Intent();
                intent.setData(Uri.parse(pdfs.getUrl()));
                startActivity(intent);
            }
        });
    }

    private void viewAllFiles() {
        databaseReference = FirebaseDatabase.getInstance("https://placement-b0be2-default-rtdb.firebaseio.com/").getReference("Uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postsnapshot:dataSnapshot.getChildren()){
                    material  pdfs=postsnapshot.getValue(material.class);
                    uploading.add(pdfs);
                }
                String[] uploads=new String[uploading.size()];
                for(int i=0;i<uploads.length;i++){
                    uploads[i]=uploading.get(i).getName();

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view=super.getView(position, convertView, parent);
                        TextView mytext=(TextView)view.findViewById(android.R.id.text1);
                        mytext.setTextColor(Color.rgb(99,99,96));
                        return view;
                    }
                };

                mypdflist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}