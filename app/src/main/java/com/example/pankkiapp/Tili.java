package com.example.pankkiapp;

public class Tili {
    String accountname;
    String accountnumber;
    double money;
    int id;

    public Tili(int id, String accountname, String accountnumber) {
        this.accountname = accountname;
        this.money=0;
        this.accountnumber = accountnumber;
        this.id=id;
    }

    public Tili() {
        this.accountname=null;
        this.accountnumber=null;
        this.money=0;
        this.id=0;
    }




    public String getAccountname() {
        return accountname;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public double getMoney() {
        return money;
    }

    public int getId() {
        return id;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setId(int id) {
        this.id = id;
    }
}
