package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Return extends AppCompatActivity {
TextView tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_return );
        Intent intent=getIntent();
        final String usn=intent.getStringExtra("added");
        tb=(TextView)findViewById( R.id.tbb );
        tb.setText( usn );

    }
}
