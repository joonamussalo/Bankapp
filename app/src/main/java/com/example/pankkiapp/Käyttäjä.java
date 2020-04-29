package com.example.pankkiapp;

import java.util.ArrayList;

public class Käyttäjä {
    String name;
    String password;
    String email;
    ArrayList<String>accountList = new ArrayList<>();


    public Käyttäjä(){
        this.name=null;
        this.password=null;
        this.email=null;

    }




    public Käyttäjä(String name, String password, String email) {
        this.name=name;
        this.password=password;
        this.email=email;
    }




    public void muokkaaTietoja() {

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
