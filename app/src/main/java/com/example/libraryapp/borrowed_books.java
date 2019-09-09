package com.example.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    borrow user;
    Button showbb;
    DatabaseReference databasebooks;
    private FirebaseAuth mAuth;
    FirebaseUser fuser;
    ArrayList<String> list;
    TextView tb;
    ArrayAdapter<String> adapter;
TextView no;



    @Override
    protected void onCreate ( Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowed_books);
        database = FirebaseDatabase.getInstance();
        user=new borrow();
        Intent intent=getIntent();
        final String usn=intent.getStringExtra("usn");

        list = new ArrayList<>();
no=(TextView)findViewById( R.id.no );
        listview = findViewById(R.id.listview2);
        adapter = new ArrayAdapter<String>(this, R.layout.layout,R.id.textView5, list);

        ref = FirebaseDatabase.getInstance().getReference().child( "borrow" ).child(usn);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int count= (int) dataSnapshot.getChildrenCount();
                no.setText( "Borrowed Books"+count );
                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                    user = ds.getValue(borrow.class);
                    if (user == null) throw new AssertionError();
                    list.add(String.format("BOOK ID:%s\nBOOK NAME:%s\nBOOK AUTHOR:%s\nBOOK EDITION:%s\nDATE:%s\n", user.getBookid(), user.getBookname(), user.getBookauthor(), user.getBookedition(), user.getDate()));
                }
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value

            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String clickedvalue = list.get(i).toString();


                Intent intent = new Intent(getApplicationContext(), Return.class);
                intent.putExtra("added", clickedvalue);
                startActivity(intent);


            }
        });



    }


}

