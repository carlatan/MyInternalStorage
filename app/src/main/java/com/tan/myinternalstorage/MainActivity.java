package com.tan.myinternalstorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText user;
    EditText pass;
    Button share;
    Button internal;
    Button next;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText) findViewById(R.id.et_user);
        pass = (EditText) findViewById(R.id.et_pass);
        share = (Button) findViewById(R.id.btn_shared);
        internal = (Button) findViewById(R.id.btn_internal);
        next = (Button) findViewById(R.id.btn_next);
    }
    public void savepref (View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", user.getText().toString());
        editor.putString("password", pass.getText().toString());
        editor.commit();
        Toast.makeText(this, "Your data has been saved! >> Tap next to display", Toast.LENGTH_SHORT).show();

    }
    public void savestore (View view) {
        String username = user.getText().toString();
        String space = ("\r\n");
        String password = pass.getText().toString();
        try {
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(space.getBytes());
            fos.write(password.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Your data has been Saved! >> Tap next to display", Toast.LENGTH_SHORT).show();
    }

    public void nextact (View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}