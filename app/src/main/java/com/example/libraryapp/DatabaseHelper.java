package com.example.libraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LibApp.db";
    public static final String TABLE_NAME1 = "ADMIN_TABLE";
    public static final String COL1 = "ID";
    public static final String COL2 = "BOOK_ID";
    public static final String COL3 = "NAME_BOOK";
    public static final String COL4 = "AUTHOR";
    public static final String COL5 = "EDITION";
    public static final String COL6 = "ISSUED";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,BOOK_ID Text,NAME_BOOK TEXT,AUTHOR TEXT,EDITION TEXT,ISSUED TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);

    }
    public boolean insertData(String bookid,String name,String author,String edition)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,bookid);
        contentValues.put(COL3,name);
        contentValues.put(COL4,author);
        contentValues.put(COL5,edition);
        long result = db.insert(TABLE_NAME1,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
