package com.sk.bankingapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(8811111111,'Sheetal',8694.00,'sheetal@gmail.com','XXXXXXXXXXXX4475','ABC09876543')");
        db.execSQL("insert into user_table values(4845121213,'Neha',875.00,'neha@gmail.com','XXXXXXXXXXXX7845','SDF42121245')");
        db.execSQL("insert into user_table values(7891521132,'Sarthak',2541.87,'sahil@gmail.com','XXXXXXXXXXXX0012','CAB54321098')");
        db.execSQL("insert into user_table values(5421202116,'Rutuj',8754.01,'rutuj@gmail.com','XXXXXXXXXXXX4578','ABC76543210')");
        db.execSQL("insert into user_table values(0012156465,'Megha',1500.10,'megha@gmail.com','XXXXXXXXXXXX645','BCA65432109')");
        db.execSQL("insert into user_table values(8000000000,'Sakshi',874.20,'sakshi@gmail.com','XXXXXXXXXXXX2354','FDR76354213')");
        db.execSQL("insert into user_table values(8684515123,'Shivani',456.00,'deepanshu@gmail.com','XXXXXXXXXXXX1022','ABC43210987')");
        db.execSQL("insert into user_table values(8641212321,'Kavya',1254.84,'kavya@gmail.com','XXXXXXXXXXXX1574','BCA32109876')");
        db.execSQL("insert into user_table values(5848451216,'Gauri',8754.12,'gauri@gmail.com','XXXXXXXXXXXX7710','CAB21098765')");
        db.execSQL("insert into user_table values(4515322315,'Shruti',5641.10,'shruti@gmail.com','XXXXXXXXXXXX1213','PTR78452596')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
