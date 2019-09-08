package com.example.libraryapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class borrowed_books extends AppCompatActivity {
    ListView listview;
    FirebaseDatabase database;
    DatabaseReference ref;
    Bookstodb user;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    EditText searchtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_borrowed_books );
        database = FirebaseDatabase.getInstance();
        ref = database.getReference( "books" );
        user = new Bookstodb();
        list = new ArrayList<>();
        listview = findViewById( R.id.listview );


        adapter = new ArrayAdapter<String>( this, R.layout.layout, R.id.textView5, list );

        // Read from the database
        ref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    user = ds.getValue( Bookstodb.class );
                    if (user == null) throw new AssertionError();
                    list.add( String.format( "BOOK ID:%s\nBOOK NAME:%s\nBOOK AUTHOR:%s\nBOOK EDITION:%s\nBOOK QUANTITY:%s", user.getBookid(), user.getBook_name(), user.getBook_author(), user.getBook_edition(), user.getBook_quantity() ) );
                }
                listview.setAdapter( adapter );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        } );
    }
}