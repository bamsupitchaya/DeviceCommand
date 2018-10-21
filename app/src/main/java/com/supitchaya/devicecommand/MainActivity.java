package com.supitchaya.devicecommand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] strings = new String[]{
            "one",
            "two",
            "three"
    };

    private String string = "Plase Speak Command";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowCommand(string);
    }// main method

    private void ShowCommand(String teststring){
        Toast.makeText(MainActivity.this, teststring, Toast.LENGTH_SHORT).show();
    }



} //main class
