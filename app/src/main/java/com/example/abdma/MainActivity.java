package com.example.abdma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;


import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    Button aboutUs, accounts, locateService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutUs = (Button)findViewById(R.id.btnAboutUs);
        accounts = (Button)findViewById(R.id.btnAccounts);
        locateService = (Button)findViewById(R.id.btnLocateService);



        aboutUs.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
            startActivity(intent);
        });

        accounts.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),AccountsActivity.class);
            startActivity(intent);
        });

        locateService.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),LocateService.class);
           startActivity(intent);
       });

        


    }
}