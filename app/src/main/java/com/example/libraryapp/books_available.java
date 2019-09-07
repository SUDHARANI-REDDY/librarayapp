package com.example.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class books_available extends AppCompatActivity {
    DatabaseReference reference;
    ArrayList<Bookstodb> list;
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_available);
        reference=FirebaseDatabase.getInstance().getReference().child("books");
        recyclerView=findViewById(R.id.rv);
        searchView=findViewById(R.id.search_book);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (reference!=null)
        {
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        list=new ArrayList<>();
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            list.add(ds.getValue(Bookstodb.class));
                        }
                        AdapterClass adapterClass=new AdapterClass(list);
                        recyclerView.setAdapter(adapterClass);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(books_available.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (searchView!=null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    search(s);
                    return true;
                }
            });
        }
    }
    private void search(String str){
        ArrayList<Bookstodb> Mylist=new ArrayList<>();
        for (Bookstodb object:list){
            if (object.getBook_author().toLowerCase().contains(str.toLowerCase())||object.getBook_name().toLowerCase().contains((str.toLowerCase()))||object.getBookid().toLowerCase().contains(str.toLowerCase())){
                Mylist.add(object);
            }
        }
        AdapterClass adapterClass=new AdapterClass(Mylist);
        recyclerView.setAdapter(adapterClass);
    }


}
