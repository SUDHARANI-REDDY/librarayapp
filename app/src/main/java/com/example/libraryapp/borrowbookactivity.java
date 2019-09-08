package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class borrowbookactivity extends AppCompatActivity {

    TextView bookn,booki,booka,bookq,booke;
    Button borrow;
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
        String bookname=intent.getStringExtra("book name");
        String bookid=intent.getStringExtra("book id");
        String bookauthor=intent.getStringExtra("book author");
        String bookedition=intent.getStringExtra("book edition");


        bookn.setText("BOOK NAME: "+bookname);
        booki.setText("BOOK ID: "+bookid);
        booka.setText("BOOK AUTHOR: "+bookauthor);
        booke.setText("BOOK EDITION: "+bookedition);


    }
}
