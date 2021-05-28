package com.android.cgpaapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CGPAapp.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE Students (ROLLNUMBER INTEGER PRIMARY KEY ," +
                    " USERNAME TEXT," +
                    " CGPA REAL)";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void insertData(int rollnumber,String username, double CG, SQLiteDatabase db)
    {
        ContentValues values= new ContentValues();
        values.put("ROLLNUMBER",rollnumber);
        values.put("USERNAME",username);
        values.put("CGPA",CG);
        db.insert("Students",null,values);
    }
    public void updateDetails( String rollnumber,String username, String CG){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ROLLNUMBER",rollnumber);
        values.put("USERNAME",username);
        values.put("CGPA",CG);
        db.update("Students" , values , "ROLLNUMBER=?" , new String[]{rollnumber});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}