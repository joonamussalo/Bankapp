package com.example.pankkiapp;

import android.content.Context;
import android.util.Log;
import android.view.View;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.os.ParcelFileDescriptor.MODE_APPEND;


public class Filewriter {
    Context context;

    public Filewriter(Context context) {
        this.context=context;
    }


    public void writeFile(String filename, String s) {



        try {
            OutputStreamWriter ows =new OutputStreamWriter(context.openFileOutput(filename,Context.MODE_APPEND));



            ows.write(s);
            ows.close();





        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("kirjoitettu");
        }
    }

}
