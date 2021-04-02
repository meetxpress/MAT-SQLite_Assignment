package com.example.sql_lite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    DbHelper dh;
    Button btnLogin, btnRegisterHere;
    EditText sno, sname, sage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dh = new DbHelper(getApplicationContext());
        SQLiteDatabase db = dh.getReadableDatabase();

        sno = findViewById(R.id.sno);
        sname = findViewById(R.id.sname);
        sage = findViewById(R.id.sage);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterHere = findViewById(R.id.btnRegisterHere);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int stdno = Integer.parseInt(sno.getText().toString());
                String stdname = sname.getText().toString();
                int stdage = Integer.parseInt(sage.getText().toString());
                if (sno.getText().toString() == "" || stdname == "" || sage.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                } else {
                    dh = new DbHelper(getApplicationContext());
                    Cursor c = dh.display(stdno);
                    if (c.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                    } else {
                        StringBuffer sb = new StringBuffer();
                        c.moveToNext();
                        sb.append("Student No: " + c.getString(0) + "\n");
                        sb.append("Student Name: " + c.getString(1) + "\n");
                        sb.append("Student Age: " + c.getString(2) + "\n\n");
                        if (c.getInt(0) == stdno) {
                            startActivity(new Intent(getApplicationContext(), UpdateDeletePage.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Inputs. Please Check you Input again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        btnRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterPage.class));
            }
        });
    }
}