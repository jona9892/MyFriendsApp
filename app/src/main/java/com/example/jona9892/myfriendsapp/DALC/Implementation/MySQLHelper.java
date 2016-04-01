package com.example.jona9892.myfriendsapp.DALC.Implementation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jona9892 on 01-04-2016.
 */
public class MySQLHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "friend.db";
    private final static int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Friend";


    MySQLHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + "(id INTEGER PRIMARY KEY, name TEXT, phoneNumber INTEGER, email TEXT, address TEXT, url TEXT, filePath TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
