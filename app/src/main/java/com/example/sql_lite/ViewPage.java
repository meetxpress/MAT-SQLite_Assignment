package com.example.sql_lite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ViewPage extends AppCompatActivity {
    DbHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);

        dh = new DbHelper(getApplicationContext());
        Cursor c = dh.displayAll();
        if (c.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
        }
        StringBuffer sb = new StringBuffer();
        while (c.moveToNext()) {
            sb.append("Student No: " + c.getString(0) + "\n");
            sb.append("Student Name: " + c.getString(1) + "\n");
            sb.append("Student Age: " + c.getString(2) + "\n\n");
        }
        AlertDialog.Builder adb = new AlertDialog.Builder(ViewPage.this);
        adb.setCancelable(true)
            .setTitle("Registered Students")
            .setMessage(sb.toString())
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            })
            .show();
    }
}