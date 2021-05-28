package com.android.courseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CourseApp.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE Students (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " USERNAME INTEGER," +
                    " PASSWORD TEXT," +
                    " OS INTEGER DEFAULT 0," +
                    " DS INTEGER DEFAULT 0," +
                    " AL INTEGER DEFAULT 0," +
                    " APP DEFAULT 0, " +
                    "REG INTEGER DEFAULT 0)";
    private static final String SQL_INSERT_ENTRY_1 =
            "INSERT INTO Students VALUES ('1','106118036','Harshit','0','0','0','0','0')";
    private static final String SQL_INSERT_ENTRY_2 =
            "INSERT INTO Students VALUES ('2','106118028','Vijitendra','0','0','0','0','0')";
    private static final String SQL_INSERT_ENTRY_3 =
            "INSERT INTO Students VALUES ('3','106118017','Avi','0','0','0','0','0')";
    private static final String SQL_INSERT_ENTRY_4 =
            "INSERT INTO Students VALUES ('4','106118010','Anjaneya','0','0','0','0','0')";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_INSERT_ENTRY_1);
        db.execSQL(SQL_INSERT_ENTRY_2);
        db.execSQL(SQL_INSERT_ENTRY_3);
        db.execSQL(SQL_INSERT_ENTRY_4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
