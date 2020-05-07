package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class activity_register extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confpass;
    EditText emailaddress;
    Button login;
    DatabaseHelper db;
    Context context;
    Filewriter fw;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db= new DatabaseHelper(this);
        fw = new Filewriter(this);



        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        emailaddress = (EditText)findViewById(R.id.emailaddress);
        confpass= (EditText)findViewById(R.id.confpass);
        // login napilla pääsee takaisin kirjautumis sivulle, jos ei haluakaan rekisteröityä
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent loginIntent= new Intent(activity_register.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });
    }
    // käyttäjä syöttää nimen, sähköpostin ja salasanan. jos salasanat ovat samoja, luodaan käyttäjä
    // ja lisätään databaseen tiedot. tiedot lisätään myös json fileen.
    public void setRegister(View v) {

        String user = username.getText().toString().trim();
        String email = emailaddress.getText().toString();
        String pass = password.getText().toString().trim();
        String confirmpass = confpass.getText().toString().trim();
        if (pass.equals(confirmpass)) {
            Käyttäjä käyttäjä = new Käyttäjä(user,pass,email);
            db.addUser(käyttäjä);
            try {


                String jsonuser = Jsoner.serialize(käyttäjä);

                fw.writeFile("user.json",jsonuser);


            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Intent goLogin= new Intent (activity_register.this,MainActivity.class);
            startActivity(goLogin);

        }else {
        }
        }
}
