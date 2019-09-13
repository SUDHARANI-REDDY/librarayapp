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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivityS extends AppCompatActivity {
    EditText email,pass;
    Button signin,reg;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_s );
        email=(EditText)findViewById( R.id.email );
        pass= (EditText)findViewById( R.id.pass );
        signin=(Button)findViewById( R.id.signin );
        reg=(Button)findViewById( R.id.register );
        mAuth = FirebaseAuth.getInstance();

        auth=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=mAuth.getCurrentUser();
                if(user!=null)
                {
                    startActivity( new Intent( getApplicationContext(),student_afterLogin.class ) );
                }
                else
                {
                    Toast.makeText( getApplicationContext(),"plz log in",Toast.LENGTH_SHORT ).show();
                }
            }
        };

        reg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(),Register.class ) );
            }
        } );
        signin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String semail = email.getText().toString();
                final String spassword = pass.getText().toString();
                if(TextUtils.isEmpty(semail)){
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(spassword)){
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(semail, spassword)
                        .addOnCompleteListener(LoginActivityS.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    startActivity( new Intent( getApplicationContext(), student_afterLogin.class ) );

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(),"email or password incorrect",Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });



            }
        } );

    }

}