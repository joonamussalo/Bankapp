package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class activity_createAccount extends AppCompatActivity {
    Button button;
    TextView textView;
    EditText edittext;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        button = (Button)findViewById(R.id.button);
        edittext=(EditText)findViewById(R.id.edittext);
        textView = (TextView)findViewById(R.id.textView);
        db=new DatabaseHelper(this);


    }
    public void createAccount(View v) {
        Intent homeintent= getIntent();
        Integer id = homeintent.getIntExtra("ID",0);
        String accountname = edittext.getText().toString();
        char[] chars = "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder(16);
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        String accountnumber = "FI"+output;
        Tili account = new Tili(id, accountname,accountnumber);
        db.addAccount(account);
        Intent intent = new Intent(activity_createAccount.this,activity_home.class);
        intent.putExtra("ID",id);
        startActivity(intent);




    }
}
