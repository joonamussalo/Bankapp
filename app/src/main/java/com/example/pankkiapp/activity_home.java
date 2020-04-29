package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class activity_home extends AppCompatActivity {

    Button button2;
    Button button1;
    Spinner spinner;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        spinner = (Spinner)findViewById(R.id.spinner);
        db = new DatabaseHelper(this);


    }

    public void changeDetails(View v) {
        Intent homeintent = getIntent();
        Integer id = homeintent.getIntExtra("ID",0);
        Intent intent = new Intent(activity_home.this,activity_changeDetails.class);
        intent.putExtra("ID",id);
        startActivity(intent);
    }

    public void createAccount(View v) {
        Intent homeintent = getIntent();
        Intent intent = new Intent(activity_home.this,activity_createAccount.class);
        Integer id = homeintent.getIntExtra("ID",0);
        intent.putExtra("ID",id);
        startActivity(intent);
    }

    public void updateAccounts(View v) {
        Intent homeintent= getIntent();
        Integer id =  homeintent.getIntExtra("ID",0);
        ArrayList<String> accountList = db.getAccounts(id);
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, accountList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }


    public void gotoAccount(View v) {
        Intent homeintent = getIntent();
        Integer id =  homeintent.getIntExtra("ID",0);
        ArrayList<String> accountList = db.getAccounts(id);


        String accountname= spinner.getSelectedItem().toString();
        Intent intent = new Intent(activity_home.this,activity_accountView.class);
        intent.putExtra("ID", id);
        intent.putExtra("accountname",accountname);
        startActivity(intent);

    }
}
