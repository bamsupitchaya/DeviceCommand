package com.supitchaya.devicecommand;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

   // public void senttoFragment ()

    private String[] strings = new String[]{
            "TV",
            "lighter",
            "radio"
    };

    private String string = "Plase Speak Command";

    private boolean aBoolean = true;

    private TextView tvTextView , lightTTextView, radioTextView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




      //  Mic Controller
        micController();

        tvTextView = findViewById(R.id.txtTV);
        lightTTextView = findViewById(R.id.txtLighter);
        radioTextView = findViewById(R.id.txtRadio);

        updateStatus();

    }// main method

    private void updateStatus() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map map = (Map) dataSnapshot.getValue();

                tvTextView.setText(strings[0]+" "+String.valueOf(map.get("TV")));
                lightTTextView.setText(strings[1]+ " " + String.valueOf(map.get("lighter")) );
                radioTextView.setText(strings[2]+ " " + String.valueOf(map.get("radio")));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            final String resultString = arrayList.get(0);
            //ShowCommand(resultString);

            for (int i=0; i<strings.length; i += 1){

                if (resultString.equals(strings[i])) {
                    aBoolean = false;
                } //if

            }//for
            if (aBoolean) {
//                No Command
                ShowCommand("No"+resultString + "in Command");
            } else {
//                Update Command On Firebase
                ShowCommand(" Upload "+resultString + " To Firebase");

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference databaseReference = firebaseDatabase.getReference(); //เชื่อมต่อดาต้าเบส

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                        Map map = (Map) dataSnapshot.getValue();
                        Map<String,Object>stringObjectsMap = new HashMap<>();
                       // stringObjectsMap.put("Command",resultString);

                       if  (resultString.equals(strings[0])){
                            stringObjectsMap.put("TV","on");
                       }else if (resultString.equals(strings[1])){
                            stringObjectsMap.put("lighter","on");
                       }else {
                           stringObjectsMap.put("radio", "on");
                       }//check device change

                        databaseReference.updateChildren(stringObjectsMap);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            } // if
        }
    }

    private void micController() {
        ImageView imageView = findViewById(R.id.imvMicrophone);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aBoolean = true;

                ShowCommand(string);

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Cannot Use Speak To Text");

                try {

                    startActivityForResult(intent, 100);

                }catch (Exception e){
                    e.printStackTrace();
                }


            } // on click
        });
    }

    private void ShowCommand(String teststring){
        Toast.makeText(MainActivity.this, teststring, Toast.LENGTH_SHORT).show();
    }



} //main class
