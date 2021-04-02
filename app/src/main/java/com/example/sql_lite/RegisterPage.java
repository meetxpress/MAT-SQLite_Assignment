package com.example.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    DbHelper dh;
    EditText reg_sno, reg_sname, reg_sage;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        dh = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        reg_sno = findViewById(R.id.reg_sno);
        reg_sname = findViewById(R.id.reg_sname);
        reg_sage = findViewById(R.id.reg_sage);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sno = reg_sno.getText().toString();
                String sname = reg_sname.getText().toString();
                String sage = reg_sage.getText().toString();
                if (sno == "" || sname == "" || sage == "") {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    String i = dh.insert(sname, sage) ? "Inserted Successfully" : "Something went Wrong";
                    Toast.makeText(getApplicationContext(), i, Toast.LENGTH_LONG).show();
                    reg_sname.setText("");
                    reg_sage.setText("");
                    startActivity(new Intent(getApplicationContext(), ViewPage.class));
                }
            }
        });
    }
}