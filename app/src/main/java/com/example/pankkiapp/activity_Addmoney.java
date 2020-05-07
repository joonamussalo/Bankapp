package com.example.pankkiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_Addmoney extends AppCompatActivity {

    Button button;
    EditText editText;
    DatabaseHelper db;
    Filewriter fw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__addmoney);
        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        db= new DatabaseHelper(this);
        fw = new Filewriter(this);

    }

    // add money nappi ottaa syötteenä käyttäjän antaman rahamäärän ja lisää sen vanhaan rahamäärään.
    //rahan lisäys tapahtuma lisätään myös historia tiedostoon.
    public void addMoney(View v) {
        Intent intent = getIntent();
        Integer id = intent.getIntExtra("ID",0);
        String accname = intent.getStringExtra("accountname");
        String stringmoney = editText.getText().toString();
        double money = Double.valueOf(stringmoney);
        double oldmoney = db.getAccountmoney(id,accname);
        double newmoney = money+oldmoney;
        Integer nimi = db.getAccountID(accname,id);
        String filename = nimi.toString();


        db.updateMoney(id,accname,newmoney);
        String toFile = "Added "+stringmoney+" to account.\n";
        fw.writeFile(filename,toFile);


        Intent moneyIntent = new Intent(activity_Addmoney.this,activity_accountView.class);
        moneyIntent.putExtra("ID",id);
        moneyIntent.putExtra("accountname",accname);
        startActivity(moneyIntent);

    }
}
