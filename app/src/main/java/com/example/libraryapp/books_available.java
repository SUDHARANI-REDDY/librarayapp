package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

public class books_available extends AppCompatActivity {
    DatabaseHelper myDB;
    AutoCompleteTextView search ;
    Button searchbutton;

    private  String[] books={"ENGINEERING MATHEMATICS","SOFTWARE ENGINEERING ","DATA COMMUNICATION","DESIGN AND ANALYSIS OF ALGORITHM","MICROPROCESSOR AND MICROCONTROLLER"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_available);


        search=findViewById(R.id.autoCompleteTextView);
        searchbutton=findViewById(R.id.SEARCH);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,books);
        search.setThreshold(1);
        search.setAdapter(adapter);


    }


    public void search_book(View view) {
        String s=search.getText().toString();
        Intent myintent=new Intent(view.getContext(),DISPLAYBOOKS.class);
        myintent.putExtra("mybook",s);
        startActivity(myintent);

    }
}
