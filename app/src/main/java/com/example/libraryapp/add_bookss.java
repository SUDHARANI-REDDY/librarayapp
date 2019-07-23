package com.example.libraryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_bookss extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText editbookcode,editname,editauthor,editedition;
    Button addbook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookss);

        myDB = new DatabaseHelper(this);

        editbookcode=(EditText) findViewById(R.id.edit_code);
        editname=(EditText) findViewById(R.id.edit_name);
        editauthor=(EditText) findViewById(R.id.edit_author);
        editedition=(EditText) findViewById(R.id.edit_edition);
        addbook=(Button) findViewById(R.id.add_book);



    }
    public void ADDBOOKTODB(View view) {
        boolean isInserted = myDB.insertData(editbookcode.getText().toString(),
                editname.getText().toString(),
                editauthor.getText().toString(),
                editedition.getText().toString());

        if (isInserted == true)
            Toast.makeText(add_bookss.this, "Book Added", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(add_bookss.this, "Book Not Added", Toast.LENGTH_LONG).show();
    }

}


