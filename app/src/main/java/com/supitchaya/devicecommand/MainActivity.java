package com.supitchaya.devicecommand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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




      //  Mic Controller
        micController();
    }// main method

    private void micController() {
        ImageView imageView = findViewById(R.id.imvMicrophone);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowCommand(string);

            } // on click
        });
    }

    private void ShowCommand(String teststring){
        Toast.makeText(MainActivity.this, teststring, Toast.LENGTH_SHORT).show();
    }



} //main class
