package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_accountView extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    TextView textView;
    TextView textView2;
    Spinner spinner;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_view);


        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        spinner = (Spinner)findViewById(R.id.spinner);
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        db=new DatabaseHelper(this);

        //haetaan databasesta tilin rahamäärä

        double money = db.getAccountmoney(id,accountname);
        String money2 = String.valueOf(money);
        textView.setText(accountname);
        textView2.setText(money2);



    }
    //add money nappi avaa rahanlisäys näkymän

    public void addMoney(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        Intent moneyintent = new Intent(activity_accountView.this,activity_Addmoney.class);
        moneyintent.putExtra("ID",id);
        moneyintent.putExtra("accountname", accountname);
        startActivity(moneyintent);
    }
    //addcard nappi avaa kortin lisäys näkymän
    public void addCard(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        Intent cardintent = new Intent(activity_accountView.this,activity_createCard.class);
        cardintent.putExtra("ID",id);
        cardintent.putExtra("accountname", accountname);
        startActivity(cardintent);
    }
    // change details napilla voi vaihtaa tilin tietoja
    public void changeDetails(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        Intent cardintent = new Intent(activity_accountView.this,activity_changeAccountDetails.class);
        cardintent.putExtra("ID",id);
        cardintent.putExtra("accountname", accountname);
        startActivity(cardintent);

    }

    public void updateCards(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        int accountid = db.getAccountID(accountname,id);
        ArrayList<String> cardlist = db.getCards(accountid);
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, cardlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void history(View v) {
        Intent intent= getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accountname = intent.getStringExtra("accountname");
        Intent historyintent = new Intent(activity_accountView.this,activity_history.class);
        historyintent.putExtra("ID",id);
        historyintent.putExtra("accountname",accountname);
        startActivity(historyintent);
    }

}
