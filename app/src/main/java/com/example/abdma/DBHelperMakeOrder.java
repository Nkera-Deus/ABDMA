package com.example.abdma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperMakeOrder extends SQLiteOpenHelper {
    public DBHelperMakeOrder(Context context) {
        super(context, "My Orders.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DBMakeOrder) {
        DBMakeOrder.execSQL("create Table Orders(orderId TEXT primary key, product TEXT, quantity INT, unit_cost INT, total_cost INT, destination TEXT, supplier TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DBMakeOrder, int i, int i1) {
        DBMakeOrder.execSQL("Drop table if exists Orders");

    }



    public Boolean insertOrder(String orderId, String product, String quantity, String unit_cost, String total_cost, String destination, String supplier)
    {
        SQLiteDatabase DBMakeOrder =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("orderId",orderId);
        cv.put("product",product);
        cv.put("quantity",quantity);
        cv.put("unit_cost",unit_cost);
        cv.put("total_cost",total_cost);
        cv.put("destination",destination);
        cv.put("supplier",supplier);
        long result=DBMakeOrder.insert("Orders",null, cv);
        if(result==-1){
            return false;
        }else {
            return true;
        }
    }



    public Boolean deleteOrder(String orderId)
    {
        SQLiteDatabase DBMakeOrder =this.getWritableDatabase();

        Cursor cursor = DBMakeOrder.rawQuery("Select * from Orders where name=?",new String[]{orderId});
        if(cursor.getCount()>0) {

            long result = DBMakeOrder.delete("Orders", "orderId=?", new String[]{orderId});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else
        {
            return false;
        }
    }

    public Cursor getOrder(){
        SQLiteDatabase DBMakeOrder =this.getWritableDatabase();

        Cursor cursor = DBMakeOrder.rawQuery("Select * from Orders",null);
        return cursor;

    }
}
