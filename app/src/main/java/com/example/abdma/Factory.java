package com.example.abdma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Factory extends AppCompatActivity {
    Button orders, promotions, inbox, outbox, supplyInfo, logOut;
    TextView txtbname;
    Bundle b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);

        orders = findViewById(R.id.btnOrders);
        promotions = findViewById(R.id.btnPromotions);
        inbox = findViewById(R.id.btnInbox);
        outbox = findViewById(R.id.btnOutbox);
       supplyInfo = findViewById(R.id.btnSupplyInfo);
        logOut = findViewById(R.id.btnLogOut);
        txtbname = findViewById(R.id.txtbname);


        //get data from the login activity
        Bundle b1 = getIntent().getExtras();
        String s1 = b1.getString("17.5");
        txtbname.setText(s1);





        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}