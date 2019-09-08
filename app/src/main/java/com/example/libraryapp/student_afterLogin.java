package com.example.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class student_afterLogin extends AppCompatActivity {
    Button bb, ba;
    FirebaseDatabase database;
    DatabaseReference ref;
    DatabaseReference databasebooks;
    private FirebaseAuth mAuth;
    FirebaseUser fuser;
String usn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_after_login );
        bb = (Button) findViewById( R.id.bb );
        ba = (Button) findViewById( R.id.ba );



        ba.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getApplicationContext(), books_available.class );
                startActivity( i );

            }
        } );
        bb.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userid = user.getUid();
                databasebooks = FirebaseDatabase.getInstance().getReference( "student" );
                databasebooks.addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String userid = user.getUid();
                        usn = dataSnapshot.child(userid).child( "usn" ).getValue( String.class );
                        Intent intent = new Intent(getApplicationContext(), borrowed_books.class);

                        intent.putExtra("usn", usn);
                        startActivity(intent);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );


            }
        } );

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu );
        return super.onCreateOptionsMenu(menu );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.signout)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity( new Intent( getApplicationContext(),MainActivity.class ) );
        }
        return super.onOptionsItemSelected( item );
    }
}
