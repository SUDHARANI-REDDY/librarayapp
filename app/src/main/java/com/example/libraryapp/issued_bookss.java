package com.example.libraryapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.libraryapp.R.layout.activity_issued_bookss;

public class issued_bookss extends AppCompatActivity {

    ListView listview;
    FirebaseDatabase database;
    DatabaseReference ref;
    borrow user;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    EditText searchtext;


    @Override
    protected void onCreate ( Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView( activity_issued_bookss);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("issued");
        user=new borrow();
        list = new ArrayList<>();
        listview = findViewById(R.id.listview);


        adapter = new ArrayAdapter<String>(this, R.layout.layout,R.id.textView5, list);

        // Read from the database
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    user = ds.getValue(borrow.class);
                    if (user == null) throw new AssertionError();
                    list.add(String.format("BOOK ID:%s\nBOOK NAME:%s\nBOOK AUTHOR:%s\nBOOK EDITION:%s\nDATE:%s\nUSN:%s", user.getBookid(), user.getBookname(), user.getBookauthor(), user.getBookedition(), user.getDate(),user.getUsn()));
                }
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        });





    }


}