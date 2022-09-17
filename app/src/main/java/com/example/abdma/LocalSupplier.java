package com.example.abdma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LocalSupplier extends AppCompatActivity {

    Button distributors, orders, makeOrder, supplyInfo, route, logOut;
    Bundle b1;
    TextView txtbname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_supplier);

        distributors = findViewById(R.id.btnDistributors);
        orders = findViewById(R.id.btnOrders);
        makeOrder = findViewById(R.id.btnMakeOrder);
        supplyInfo = findViewById(R.id.btnSupplyInfo);
        route = findViewById(R.id.btnRoute);
        logOut = findViewById(R.id.btnLogOut);
        txtbname =findViewById(R.id.txtbname);



        //get data from the login activity
        Bundle b1 = getIntent().getExtras();
        String s1 = b1.getString("17.5");
        txtbname.setText(s1);


        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Routing.class);
                startActivity(i);
            }
        });



        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MakeOrder.class);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}