package com.example.pankkiapp;

abstract class Kortti {

    String area;
    double withdrawlimit;
    double paymentlimit;
    int accountID;
    String cardname;
    String cardtype;


    public String getArea() {
        return area;
    }

    public double getWithdrawlimit() {
        return withdrawlimit;
    }

    public double getPaymentlimit() {
        return paymentlimit;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getCardname() {
        return cardname;
    }

    public String getCardtype() {
        return cardtype;
    }
}

class DebitKortti extends Kortti {

    public DebitKortti(String cardname, String cardtype,int id, String area,double withdrawlimit,double paymentlimit) {
        this.area=area;
        this.withdrawlimit=withdrawlimit;
        this.paymentlimit=paymentlimit;
        this.accountID=id;
        this.cardname=cardname;
        this.cardtype=cardtype;

    }

}

class CreditKortti extends Kortti {


    public CreditKortti(String cardname, String cardtype, int id, String area,double withdrawlimit,double paymentlimit) {
        this.area = area;
        this.withdrawlimit = withdrawlimit;
        this.paymentlimit = paymentlimit;
        this.accountID=id;
        this.cardname=cardname;
        this.cardtype=cardtype;
    }


}
