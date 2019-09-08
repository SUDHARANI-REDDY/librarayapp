package com.example.libraryapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText remail, rpass, con, rusername, rusn;
    Button register;
    TextView toast;
    private FirebaseAuth mAuth;
    FirebaseUser fuser;
    FirebaseDatabase mdata;
    private DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        remail = (EditText) findViewById( R.id.email );
        rpass = (EditText) findViewById( R.id.pass );
        toast = (TextView) findViewById( R.id.toast );
        rusn = (EditText) findViewById( R.id.usn );
        rusername = (EditText) findViewById( R.id.username );
        con = (EditText) findViewById( R.id.conpass );
        toast.setText( "" );
        register = (Button) findViewById( R.id.button );
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        mref = FirebaseDatabase.getInstance().getReference().child( "student" );
        mref = FirebaseDatabase.getInstance().getReference().child( "usn" );
        register.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = remail.getText().toString().trim();
                final String username = rusername.getText().toString().trim();
                final String usn = rusn.getText().toString().trim().toUpperCase();
                final String password = rpass.getText().toString();
                String confirm = con.getText().toString();

                if (TextUtils.isEmpty( email )) {
                    toast.setText( "Please enter email" );
                    return;
                }
                if (TextUtils.isEmpty( password )) {
                    toast.setText( "Please enter password" );
                    return;
                }
                if (TextUtils.isEmpty( confirm )) {
                    toast.setText( "Please enter confirm password" );
                    return;
                }
                if (TextUtils.isEmpty( username )) {
                    toast.setText( "Please enter username" );
                    return;
                }
                if (TextUtils.isEmpty( usn )) {
                    toast.setText( "Please enter usn" );

                    return;
                }
                if((usn.length()!=10)||(usn.substring( 0,2 )!="1MV")||((usn.substring( 3,4 )!="16")&&(usn.substring( 3,4 )!="17")&&(usn.substring( 3,4 )!="18")&&(usn.substring( 3,4 )!="19")))
                {
                    toast.setText( " Invalid usn" );
                    return;
                }
                FirebaseDatabase.getInstance().getReference( "usn" ).child( usn ).addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists())
                        {
                            toast.setText( "  usn already exits" );
                        }
                        else
                        {
                            FirebaseDatabase.getInstance().getReference( "usn" ).child( usn ).setValue( true );
                            mAuth.createUserWithEmailAndPassword( email, password )
                                    .addOnCompleteListener( Register.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                // Sign in success, update UI with the signed-in user's information
                                                student info = new student( email, username, usn );


                                                FirebaseDatabase.getInstance().getReference( "student" ).child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).setValue( info ).addOnCompleteListener( new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        toast.setText( " Registration Complete" );
                                                        startActivity( new Intent( getApplicationContext(),LoginActivityS.class ) );
                                                    }
                                                } );
                                            } else {
                                                // If sign in fails, display a message to the user.
                                                toast.setText( " Registration Incomplete" );

                                            }

                                            // ...
                                        }
                                    } );
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                } );


            }

        } );


    }
}

