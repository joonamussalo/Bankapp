package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class activity_changeAccountDetails extends AppCompatActivity {

    DatabaseHelper db;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_account_details2);
        db = new DatabaseHelper(this);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
    }

    public void changeDetails(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        String newname = editText.getText().toString();
        int accountID = db.getAccountID(accountname,id);

        Tili tili = new Tili();
        tili.setAccountname(newname);


        db.updateAccount(accountID,tili);
        Intent account = new Intent(activity_changeAccountDetails.this,activity_home.class);
        account.putExtra("ID",id);
        account.putExtra("accountname",accountname);

        startActivity(account);


    }


}
