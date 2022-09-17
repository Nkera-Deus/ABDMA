package com.example.abdma;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MakeOrder extends AppCompatActivity {
    TextView total_cost;

    EditText orderId, product, quantity, unit_cost, dLocation, buyingFrom;
    Button Add, Delete,View, Clear;
    DBHelperMakeOrder DB2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);


       //link with xml resources
        orderId =findViewById(R.id.edt00);
        product =findViewById(R.id.edt1);
        quantity =findViewById(R.id.edt2);
        unit_cost =findViewById(R.id.edt3);
        dLocation =findViewById(R.id.edt5);
        buyingFrom =findViewById(R.id.edt6);
        total_cost =findViewById(R.id.txtTotalCost);


        Add =findViewById(R.id.btn1);
        Delete =findViewById(R.id.btn2);
        View =findViewById(R.id.btn3);
        Clear =findViewById(R.id.btn4);


        DB2 = new DBHelperMakeOrder(this);

        //...............................................................................
        // clear the text in the feilds
        orderId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {


            }
        });
//.............................................................................................
        //what should happen when i click this button
        Add.setOnClickListener(view -> {

            String orderIdTXT = orderId.getText().toString();
            String productTXT = product.getText().toString();
            String quantityTXT = quantity.getText().toString();
            String unitCostTXT = unit_cost.getText().toString();
            myTotalCost( quantityTXT, unitCostTXT);
            String totalCostTXT = total_cost.getText().toString();
            String dLocationTXT = dLocation.getText().toString();
            String buyingFromTXT = buyingFrom.getText().toString();

            Boolean checkOrderData = DB2.insertOrder(orderIdTXT, productTXT, quantityTXT, unitCostTXT, totalCostTXT, dLocationTXT,buyingFromTXT);
            if(checkOrderData  && !orderIdTXT.equals("")){
                Toast.makeText(MakeOrder.this,"New Order Added", Toast.LENGTH_SHORT).show();
                product.setText("");
                quantity.setText("");
                unit_cost.setText("");
                total_cost.setText("");
                dLocation.setText("");
                buyingFrom.setText("");

            }
            else{
                Toast.makeText(MakeOrder.this,"New Order Not Added", Toast.LENGTH_SHORT).show();
            }

        });
//...........................................................................................
        //deleting an order
        Delete.setOnClickListener(view -> {
            String orderIdTXT = orderId.getText().toString();


            Boolean checkdeleteOrder = DB2.deleteOrder(orderIdTXT);
            if (checkdeleteOrder)
                Toast.makeText(MakeOrder.this, "Order Deleted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MakeOrder.this, "Order Not Deleted", Toast.LENGTH_SHORT).show();
        });
        //.......................................................................................

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = DB2.getOrder();
                if (res.getCount() == 0) {
                    Toast.makeText(MakeOrder.this, "No Order Exits", Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                int i;
                while (res.moveToNext()) {
                    buffer.append("Order ID:" + res.getString(0) + "\n");
                    buffer.append("Product:" + res.getString(1) + "\n");
                    buffer.append("Quantity:" + res.getString(2) + "\n");
                    buffer.append("Unit cost:" + res.getString(3) + "\n");
                    buffer.append("Total cost:" + res.getString(4) + "\n");
                    buffer.append("Destination:" + res.getString(5) + "\n");
                    buffer.append("Buying from:" + res.getString(6) + "\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MakeOrder.this);
                builder.setCancelable(true);
                builder.setTitle("Order");
                builder.setMessage(buffer.toString());
                builder.show();
            }


        });


    }
    //.................................................................................................
//this method multiplies the quantity of products and their unit cost to generate a total cost
    public void myTotalCost( String quantityTXT, String unitCostTXT){
        if(quantityTXT.equals("")&&unitCostTXT.equals("")){
            Toast.makeText(this, "No Values Yet", Toast.LENGTH_SHORT).show();
        }else {
            quantityTXT = quantity.getText().toString();
            unitCostTXT = unit_cost.getText().toString();
            int quan = Integer.parseInt(quantityTXT);
            int uCost = Integer.parseInt(unitCostTXT);
            //a is the quantity while b is the unit cost
            int prod = quan * uCost;
            String s = String.valueOf(prod);
            total_cost.setText(s);
        }

    }
}