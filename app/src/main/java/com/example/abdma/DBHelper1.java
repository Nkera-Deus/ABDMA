package com.example.abdma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DBHelper1 extends SQLiteOpenHelper {
    public DBHelper1(Context context) {
        super(context,"Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB1) {
      DB1.execSQL("create Table Users(bName TEXT primary key, bType TEXT, location String, contact INT, userName String, password String)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB1, int i, int i1) {
        DB1.execSQL("drop Table if exists Users");

    }
    public Boolean insertUserData(String bName, String bType, String location,String contact, String userName, String password ){
        SQLiteDatabase DB1 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bName", bName);
        cv.put("bType", bType);
        cv.put("location", location);
        cv.put("contact", contact);
        cv.put("userName", userName);
        cv.put("password", password);
        long result = DB1.insert("Users",null, cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean checkusername(String username){
        SQLiteDatabase DB1 = this.getWritableDatabase();
        Cursor cursor = DB1.rawQuery("select * from Users where username =?", new String[]{username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkbusinessname(String bName){
        SQLiteDatabase DB1 = this.getWritableDatabase();
        Cursor cursor = DB1.rawQuery("select * from Users where bName = ?", new String[]{bName});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;


    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase DB1 = this.getWritableDatabase();
        Cursor cursor = DB1.rawQuery("select * from Users where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;

    }
    public Boolean checkbType(String bType){
        SQLiteDatabase DB1 = this.getWritableDatabase();
        Cursor cursor = DB1.rawQuery("select * from Users where bType=?",new String[]{bType});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
 


}
