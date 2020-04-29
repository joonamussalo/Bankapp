package com.example.pankkiapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="taneli.db";
    public static final String TABLE_NAME="users";
    public static final String TABLE_NAME2="accounts";
    public static final String TABLE_NAME3="cards";
    public static final String COL_1="ID";
    public static final String COL_2="name";
    public static final String COL_3="password";
    public static final String COL_4="email";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql ="CREATE TABLE users (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,password TEXT,email TEXT)";
        String sql2="CREATE TABLE accounts (accountID INTEGER PRIMARY KEY AUTOINCREMENT, ID INTEGER, accountname TEXT, accountnumber TEXT, money DOUBLE, FOREIGN KEY(ID)REFERENCES users(ID))";
        String sql3="CREATE TABLE cards (cardID INTEGER PRIMARY KEY AUTOINCREMENT, accountID INTEGER, cardtype TEXT, cardname TEXT, withdrawlimit INTEGER,paymentlimit INTEGER, area TEXT, FOREIGN KEY(accountID)REFERENCES accounts(accountID))";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME2);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME3);
        onCreate(sqLiteDatabase);
    }
    public void addUser(Käyttäjä käyttäjä) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",käyttäjä.getName());
        contentValues.put("password", käyttäjä.getPassword());
        contentValues.put("email",käyttäjä.getEmail());
        long res = db.insert("users",null, contentValues);
        db.close();
    }

    public void updateMoney(int id, String accname, double money) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql= "UPDATE "+TABLE_NAME2+" SET money ="+money+" WHERE ID =" +id+" AND accountname ='" +accname+"';";
        db.execSQL(sql);
    }

    public void updateUser(int id, Käyttäjä käyttäjä) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql= "UPDATE "+TABLE_NAME+" SET name ='"+käyttäjä.getName()+"', password ='"+käyttäjä.getPassword()+"', email ='"+käyttäjä.getEmail()+"' WHERE ID =" +id+";";
        db.execSQL(sql);
    }

    public void updateAccount(int id, Tili tili) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "UPDATE "+TABLE_NAME2+" SET accountname ='"+tili.getAccountname()+"' WHERE accountID =" +id+";";
        db.execSQL(sql);
    }

    public int getUserID(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql =" SELECT ID FROM "+TABLE_NAME+" WHERE name ='" +user+"';";
        Cursor res = db.rawQuery(sql, null);
        res.moveToFirst();
        String id = res.getString(0);
        int userID = Integer.parseInt(id);

      return userID;
    }

    public int getAccountID(String accname, int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql =" SELECT accountID FROM " + TABLE_NAME2+" WHERE ID =" +id+" AND accountname ='" +accname+"';";
        Cursor res = db.rawQuery(sql, null);
        res.moveToFirst();
        String ID = res.getString(0);
        int accountID = Integer.parseInt(ID);

        return accountID;
    }

    public String getAccountname(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql =" SELECT accountname FROM " + TABLE_NAME2+" WHERE ID =" +id+";";
        Cursor res = db.rawQuery(sql,null);
        res.moveToFirst();
        String name= res.getString(0);

        return name;
    }

    public double getAccountmoney(int id, String accname) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql =" SELECT money FROM " + TABLE_NAME2+" WHERE ID =" +id+" AND accountname ='" +accname+"';";
        Cursor res = db.rawQuery(sql,null);
        res.moveToFirst();
        double money= res.getDouble(0);


        return money;
    }



    public void addAccount(Tili account) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("ID",account.getId());
        contentValues.put("accountname",account.getAccountname());
        contentValues.put("accountnumber",account.getAccountnumber());
        contentValues.put("money",account.getMoney());
        long res = db.insert("accounts",null, contentValues);
        db.close();

    }

    public void addDebitCard(DebitKortti debitkortti) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cardname",debitkortti.getCardname());
        contentValues.put("cardtype",debitkortti.getCardtype());
        contentValues.put("accountID", debitkortti.getAccountID());
        contentValues.put("withdrawlimit", debitkortti.getWithdrawlimit());
        contentValues.put("paymentlimit", debitkortti.getPaymentlimit());
        contentValues.put("area",debitkortti.getArea());
        long res = db.insert("cards",null,contentValues);

    }

    public void addCreditCard(CreditKortti creditKortti) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cardname",creditKortti.getCardname());
        contentValues.put("cardtype", creditKortti.getCardtype());
        contentValues.put("accountID", creditKortti.getAccountID());
        contentValues.put("withdrawlimit", creditKortti.getWithdrawlimit());
        contentValues.put("paymentlimit", creditKortti.getPaymentlimit());
        contentValues.put("area",creditKortti.getArea());
        long res = db.insert("cards",null,contentValues);

    }


    public  ArrayList<String> getAccounts(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql =" SELECT accountname FROM "+TABLE_NAME2+" WHERE ID =" +id+";";
        Cursor res = db.rawQuery(sql, null);
        res.moveToFirst();
        ArrayList<String> accountList = new ArrayList<>();
        if ((res != null) && (res.getCount() > 0)) {
            accountList.add(res.getString(0));
            while (res.moveToNext()) {
                accountList.add(res.getString(0));
            }
            return accountList;

        } else {
            return  accountList;


        }
    }

    public  ArrayList<String> getCards(int accountid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql =" SELECT cardname FROM "+TABLE_NAME3+" WHERE accountID =" +accountid+";";
        Cursor res = db.rawQuery(sql, null);
        res.moveToFirst();
        ArrayList<String> cardList = new ArrayList<>();
        if ((res != null) && (res.getCount() > 0)) {
            cardList.add(res.getString(0));
            while (res.moveToNext()) {
                cardList.add(res.getString(0));
            }
            return cardList;

        } else {
            return  cardList;


        }
    }


    public boolean userCheck(String name, String pass) {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and "+COL_3 + "=?";
        String[] selectionArgs= {name, pass};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0) {
            return true;
        }else{
            return false;
        }
    }
}
