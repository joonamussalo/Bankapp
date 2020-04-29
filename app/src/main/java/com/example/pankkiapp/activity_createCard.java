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

public class activity_createCard extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    EditText editText3;
    Button button;
    DatabaseHelper db;
    Spinner spinner;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        db = new DatabaseHelper(this);

        ArrayList<String> debitcredit = new ArrayList<>();
        debitcredit.add("debit");
        debitcredit.add("credit");

        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, debitcredit);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayList<String> area = new ArrayList<>();
        area.add("Suomi");
        area.add("Eurooppa");
        area.add("maailma");

        ArrayAdapter<String> adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, area);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


    }

    public void addCard(View v) {
        Intent intent= getIntent();
        String accountname = intent.getStringExtra("accountname");
        int id = intent.getIntExtra("ID",0);
        int accoundID = db.getAccountID(accountname,id);
        String withdraw = editText.getText().toString();
        double withdrawlimit = Double.parseDouble(withdraw);
        String payment = editText2.getText().toString();
        double paymentlimit = Double.parseDouble(payment);
        String cardname = editText3.getText().toString();
        String cardtype = spinner.getSelectedItem().toString();

        if (spinner.getSelectedItem().toString().equals("debit")) {
            DebitKortti debitKortti = new DebitKortti(cardname,cardtype, accoundID, spinner2.getSelectedItem().toString(),withdrawlimit,paymentlimit);
            db.addDebitCard(debitKortti);
        } else {
            CreditKortti creditKortti= new CreditKortti(cardname,cardtype, accoundID,spinner2.getSelectedItem().toString(),withdrawlimit,paymentlimit);
            db.addCreditCard(creditKortti);
        }
        Intent cardintent = new Intent(activity_createCard.this,activity_accountView.class);
        cardintent.putExtra("ID",id);
        cardintent.putExtra("accountname",accountname);
        startActivity(cardintent);

    }
}
