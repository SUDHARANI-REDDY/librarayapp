package com.example.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class borrowbookactivity extends AppCompatActivity {

    TextView bookn,booki,booka,bookq,booke;
    Button borrow;
    DatabaseReference databasebooks;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    String usn;
    private FirebaseAuth.AuthStateListener auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowbookactivity);

        bookn=(TextView)findViewById(R.id.bookname1);
        booki=(TextView)findViewById(R.id.bookid1);
        booka=(TextView)findViewById(R.id.author1);
        booke=(TextView) findViewById(R.id.edition1);
        borrow=(Button) findViewById(R.id.borrow);

        Intent intent=getIntent();
         final String bookname=intent.getStringExtra("book name");
       final String bookid=intent.getStringExtra("book id");
        final String bookauthor=intent.getStringExtra("book author");
        final String bookedition=intent.getStringExtra("book edition");



         final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        bookn.setText("BOOK NAME: "+bookname);
        booki.setText("BOOK ID: "+bookid);
        booka.setText("BOOK AUTHOR: "+bookauthor);
        booke.setText("BOOK EDITION: "+bookedition);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        databasebooks = FirebaseDatabase.getInstance().getReference( "student" );
        databasebooks.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userid = user.getUid();
                usn = dataSnapshot.child(userid).child( "usn" ).getValue( String.class );


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        borrow = (Button) findViewById( R.id.borrow );

        borrow.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ex = FirebaseDatabase.getInstance().getReference("borrow");

                borrow newbook = new borrow(bookname,bookid,bookauthor,bookedition,date,usn);
                ex.child(usn).child(bookid).setValue( newbook );
                DatabaseReference y = FirebaseDatabase.getInstance().getReference("issued");
                borrow issuebook = new borrow(bookname,bookid,bookauthor,bookedition,date,usn);
                y.child(bookid).setValue( newbook );

            }


        } );


    }
}
