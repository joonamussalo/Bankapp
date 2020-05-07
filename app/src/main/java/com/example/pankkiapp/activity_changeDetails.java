package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class activity_changeDetails extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText confpass;
    EditText emailaddress;
    Button button;
    Käyttäjä käyttäjä;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_details);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        button = (Button) findViewById(R.id.button);
        emailaddress = (EditText)findViewById(R.id.emailaddress);
        confpass= (EditText)findViewById(R.id.confpass);
        db = new DatabaseHelper(this);
    }
    //changedetails metodilla voidaan asettaa uusi käyttäjänimi, salasana ja email osoite käyttäjälle. tiedot päivitetään databaseen.
    public void changeDetails(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String user = username.getText().toString().trim();
        String email = emailaddress.getText().toString();
        String pass = password.getText().toString().trim();
        String confirmpass = confpass.getText().toString().trim();
        if (pass.equals(confirmpass)) {
            Käyttäjä käyttäjä = new Käyttäjä();
            käyttäjä.setName(user);
            käyttäjä.setPassword(pass);
            käyttäjä.setEmail(email);

            db.updateUser(id, käyttäjä);

        }
        Intent homeintent = new Intent(activity_changeDetails.this,activity_home.class);
        homeintent.putExtra("ID",id);
        startActivity(homeintent);

    }
}
