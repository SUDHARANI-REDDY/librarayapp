package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class student_afterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_after_login);
    }
    public void bookavail(View view)
    {
        Intent i = new Intent(this, books_available.class);
        startActivity(i);
    }
    public void bookborrowed(View view)
    {
        Intent i = new Intent(this, borrowed_books.class);
        startActivity(i);
    }

}
