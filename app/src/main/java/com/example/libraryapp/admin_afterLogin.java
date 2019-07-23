package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class admin_afterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_after_login);
    }
    public void addbook(View view)
    {
        Intent i = new Intent(this, add_bookss.class);
        startActivity(i);
    }
    public void removebook(View view)
    {
        Intent i = new Intent(this, remove_bookss.class);
        startActivity(i);
    }
    public void issuedbooks(View view)
    {
        Intent i = new Intent(this, issued_bookss.class);
        startActivity(i);
    }
}
