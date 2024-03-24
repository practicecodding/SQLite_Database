package com.hamidul.digitalmoneybag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class  DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Digital_Moneybag", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table expense (id INTEGER primary key autoincrement,amount DOUBLE,reason TEXT,date DOUBLE)");
        db.execSQL("create table income (id INTEGER primary key autoincrement,amount DOUBLE,reason TEXT,date DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists expense");
        db.execSQL("drop table if exists income");

    }


    public void addExpense (double amount, String reason){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("date",System.currentTimeMillis());

        db.insert("expense",null,contentValues);

    }

    public void addIncome (double amount, String reason){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("date",System.currentTimeMillis());

        db.insert("income",null,contentValues);

    }

    public double calculateExpense (){
        double total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from  expense",null);
        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                double expense = cursor.getDouble(1);
                total = total+expense;
            }
        }

        return total;

    }

    public double calculateIncome (){
        double total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from  income",null);
        if (cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                double income = cursor.getDouble(1);
                total = total+income;
            }
        }

        return total;

    }


    public Cursor getAllExpenseData (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense",null);
        return cursor;
    }

    public Cursor getAllIncomeData (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income",null);
        return cursor;
    }





}
