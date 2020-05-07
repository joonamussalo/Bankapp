package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class activity_history extends AppCompatActivity {

    Button button;
    TextView textView=null;
    Context context;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        db = new DatabaseHelper(this);

        context = activity_history.this;

        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        Integer nimi = db.getAccountID(accountname,id);
        String filename = nimi.toString();
        // valitun tilin historia tulostetaan puhelimen näytölle.
        try {

            InputStream ins=context.openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s ="";
            String tiedosto="";

            while ((s=br.readLine())!=null) {

                tiedosto = tiedosto+s;


            };
            textView.setText(tiedosto);
            ins.close();


        } catch (IOException e) {
            Log.e("IOExpection","Virhe syöte");
        } finally {
            System.out.println("luettu");
        }

    }

    //go bcak vie käyttäjän takaisin edelliseen näkymään.

    public void goBack(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        Intent historyintent = new Intent(activity_history.this,activity_accountView.class);
        historyintent.putExtra("ID",id);
        historyintent.putExtra("accountname",accountname);
        startActivity(historyintent);

    }


    }

