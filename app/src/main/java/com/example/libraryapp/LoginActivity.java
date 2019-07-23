package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void StudentLogin(View view)
    {
        Intent i = new Intent(this, LoginActivityS.class);
        startActivity(i);
    }
    public void AdminLogin(View view)
    {
        Intent i = new Intent(this, LoginActivityA.class);
        startActivity(i);
    }
}
