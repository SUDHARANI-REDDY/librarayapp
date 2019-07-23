package com.example.libraryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DISPLAYBOOKS extends AppCompatActivity {
    DatabaseHelper myDB;
    ListView booklist;
    SQLiteDatabase db;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaybooks);
        myDB= new DatabaseHelper(this);
        booklist=findViewById(R.id.book_list);
        db=openOrCreateDatabase("LibApp.db",MODE_PRIVATE,null);
        text=getIntent().getStringExtra("mybook").toString();
        Cursor cursor;
        cursor=db.rawQuery("SELECT NAME_BOOK FROM ADMIN_TABLE WHERE NAME_BOOK LIKE '"+text+"';",null);
        int i=0;
        ArrayList<String> books=new ArrayList<>();
        cursor.moveToFirst();
       do
        {
            books.add(cursor.getString(0));
        } while(cursor.moveToNext());
        ArrayAdapter adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,books);
        booklist.setAdapter(adapter);
    }
}
