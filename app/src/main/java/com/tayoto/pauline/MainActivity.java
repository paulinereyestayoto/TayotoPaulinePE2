package com.tayoto.pauline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText ename, eage, egender;
    TextView tmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ename = findViewById(R.id.etname);
        eage = findViewById(R.id.etage);
        egender = findViewById(R.id.etgender);
        tmsg = findViewById(R.id.tvmsg);
    }

    public void saveInternal (View v){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data.txt", Context.MODE_PRIVATE);
            String name = ename.getText().toString() + "\n";
            String age = eage.getText().toString() + "\n";
            String gender = egender.getText().toString() + "\n";
            fos.write(name.getBytes());
            fos.write(age.getBytes());
            fos.write(gender.getBytes());
            Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error writing data...", Toast.LENGTH_LONG).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//DISPLAY INTERNAL
    public void displayInternal(View v) {
        try {
            FileInputStream fin = openFileInput("data.txt" );
            int c;
            StringBuffer buffer = new StringBuffer();
            while ((c = fin.read())!= -1) {
                buffer.append((char) c);
            }
            String message =  "" + buffer;
            tmsg.setText(message);
        } catch (Exception e) {
            Toast.makeText(this, "Error reading...", Toast.LENGTH_LONG).show();
        }
    }
}