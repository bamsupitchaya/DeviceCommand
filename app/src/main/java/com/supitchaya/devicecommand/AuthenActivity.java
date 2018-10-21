package com.supitchaya.devicecommand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AuthenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentAuthenFragment,new AuthenFragment())
                    .commit();
        }


    }
}
