package com.example.abdma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Routing extends AppCompatActivity {
    Button drive, add, save, share, route, cycle, viewmap, clear;
    EditText lat, lon, home_location, locByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing);

        add = findViewById(R.id.btnAdd);
        drive = findViewById(R.id.btndrive);
        save = findViewById(R.id.btnSave);
        share = findViewById(R.id.btnshare);
        cycle = findViewById(R.id.btnCycle);
        route = findViewById(R.id.btnRoute);
        viewmap = findViewById(R.id.btnViewMap);
        clear = findViewById(R.id.btnClear);

        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RouteChecker.class);
                startActivity(i);
            }
        });



    }
}