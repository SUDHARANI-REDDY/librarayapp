package com.example.libraryapp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class remove_bookss extends AppCompatActivity {

    public static EditText code_remove;
    public static Button deletee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_bookss);
        code_remove=(EditText) findViewById(R.id.coderemove);
        deletee=(Button) findViewById(R.id.bookremove);
    }
    public void deletebook(View view)
    {
        String bookidd=code_remove.getText().toString().trim();
        DatabaseReference delbook= FirebaseDatabase.getInstance().getReference("books").child(bookidd);
        delbook.removeValue();
        Toast.makeText(this,"BOOK DELETED",Toast.LENGTH_LONG).show();
    }
    public void scancode(View view)
    {
        Intent i = new Intent(this, barcodescan2.class);
        startActivity(i);
    }
}
