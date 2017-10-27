package com.tan.myinternalstorage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {
    TextView tv_display;
    FileInputStream fis;
    BufferedReader br;
    Button loadshared;
    Button loadinternal;
    Button clear;
    Button back;
    String user, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        loadshared = (Button) findViewById(R.id.btn_loadshared);
        loadinternal = (Button) findViewById(R.id.btn_loadinternal);
        clear = (Button) findViewById(R.id.btn_clear);
        back = (Button) findViewById(R.id.btn_back);
        tv_display = (TextView) findViewById(R.id.tvdisplay);
    }
    public void backact (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cleardisplay (View view) {
        tv_display.setText("");
    }

    public void loadpref(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String user = preferences.getString("username","");
        String pwd = preferences.getString("password","");
        tv_display.setText("User is " + user + " and Password is " + pwd);
    }

    public void loadstore (View view) throws IOException {
        String read = "";
        try{
            fis = openFileInput("output.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((read = br.readLine()) != null)
                user = read;
            if ((read = br.readLine()) != null)
                pwd = read;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_display.setText("User is " + user + " and Password is " + pwd);
    }
}