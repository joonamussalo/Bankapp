package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {
    EditText username;

    EditText password;
    Button login;
    Button register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= new DatabaseHelper(this);
        username =(EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, activity_register.class);
                startActivity(registerIntent);
            }



        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pasw = password.getText().toString().trim();
                Boolean check = db.userCheck(user,pasw);
                if(check==true) {
                    Intent homescreen = new Intent(MainActivity.this, activity_home.class);
                    homescreen.putExtra("username", user);
                    homescreen.putExtra("password",pasw);
                    int id = db.getUserID(user);


                        homescreen.putExtra("ID",id);
                        startActivity(homescreen);
                }}

        });
    }
}
