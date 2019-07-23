package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivityS extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_s);

        name=(EditText) findViewById(R.id.editText);
        password= (EditText) findViewById(R.id.editText2);
        login=(Button) findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logins(name.getText().toString(),password.getText().toString());

            }
        });
    }
    private void logins(String username, String password)
    {
        if((username.equals("1MV17CS071"))&&(password.equals("1MV17CS071")))
        {
            Intent intent=new Intent(this,student_afterLogin.class);
            startActivity(intent);
        }
        else
        {
            TextView flogin=(TextView) findViewById(R.id.falselogin);
            flogin.setText("Entered wrong username or password \nPlease try again ");
        }
    }
}
