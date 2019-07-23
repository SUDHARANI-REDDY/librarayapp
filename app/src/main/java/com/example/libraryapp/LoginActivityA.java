package com.example.libraryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivityA extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_a);

        name=(EditText) findViewById(R.id.edituser);
        password= (EditText) findViewById(R.id.editpassword);
        login=(Button) findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logina(name.getText().toString(), password.getText().toString());

            }
        });
    }
    private void logina(String username, String password)
    {
        if((username.equals("ADMIN"))&&(password.equals("ADMIN")))
        {
            Intent intent=new Intent(this,admin_afterLogin.class);
            startActivity(intent);
        }
        else
        {
            TextView flogin=(TextView) findViewById(R.id.falselogina);
            flogin.setText("Entered wrong username or password \nPlease try again ");
        }
    }
}
