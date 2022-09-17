package com.example.abdma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Retailer extends AppCompatActivity {
    Bundle b1;
    TextView txtbname;
    Button localSuppliers, sendMessage, makeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer);

        txtbname=findViewById(R.id.txtbname);
        sendMessage =findViewById(R.id.btnSendMessage);
        makeOrder = findViewById(R.id.btnMakeOrder);


        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MakeOrder.class);
                startActivity(i);
            }
        });



        //get data from the login activity
        Bundle b1 = getIntent().getExtras();
        String s1 = b1.getString("17.5");
        txtbname.setText(s1);
    }
}