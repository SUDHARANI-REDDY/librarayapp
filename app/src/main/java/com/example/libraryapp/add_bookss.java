package com.example.libraryapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_bookss extends AppCompatActivity {

    public static EditText editbookcode, editname, editauthor, editedition, editquantity;
    Button addbook, editscan, quanminus, quanplus;
    DatabaseReference databasebooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookss);

        editbookcode = (EditText) findViewById(R.id.edit_code);
        editname = (EditText) findViewById(R.id.edit_name);
        editauthor = (EditText) findViewById(R.id.edit_author);
        editedition = (EditText) findViewById(R.id.edit_edition);
        addbook = (Button) findViewById(R.id.add_book);
        editscan = (Button) findViewById(R.id.scan);
        quanminus = (Button) findViewById(R.id.quan_minus);
        quanplus = (Button) findViewById(R.id.quan_plus);
        editquantity = (EditText) findViewById(R.id.quantity);
        databasebooks = FirebaseDatabase.getInstance().getReference("books");
    }



    public void scancode(View view) {
        Intent i = new Intent(this, barcodescan.class);
        startActivity(i);
    }

    public void increase(View view) {
        String value = editquantity.getText().toString();
        int quan = Integer.parseInt(value);
        quan = quan + 1;
        editquantity.setText("" + quan);

    }

    public void decrease(View view) {
        String value = editquantity.getText().toString();
        int quan = Integer.parseInt(value);
        quan--;
        editquantity.setText("" + quan);
    }
    public void automatic(View view)
    {
        String code=editbookcode.getText().toString().trim();
        databasebooks.orderByKey().equalTo(code).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Bookstodb name1;
                for (DataSnapshot snap : dataSnapshot.getChildren()) {

                    name1=snap.getValue(Bookstodb.class);
                    editname.setText(name1.book_name);
                    editauthor.setText(name1.book_author);
                    editedition.setText(name1.book_edition);
                    name1.book_quantity+=editquantity.getText().toString().trim();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void addbooktodb(View view) {
        addbook();
    }

    public void addbook() {
        final String code = editbookcode.getText().toString().trim();
        String name = editname.getText().toString().trim();
        String author = editauthor.getText().toString().trim();
        String edition = editedition.getText().toString().trim();
        String quant = editquantity.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(author) || TextUtils.isEmpty(edition) || TextUtils.isEmpty(quant) || TextUtils.isEmpty(code)) {
            Toast.makeText(this, "Fill all the details please!", Toast.LENGTH_LONG).show();
        }

        else {
            Bookstodb newbook = new Bookstodb(code, name, author, edition, quant);
            databasebooks.child(code).setValue(newbook);
            Toast.makeText(this, "BOOK ADDED", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    editbookcode.setText("");
                    editname.setText("");
                    editauthor.setText("");
                    editedition.setText("");
                    editquantity.setText("1");
                }
            }, 600);
        }
    }
}