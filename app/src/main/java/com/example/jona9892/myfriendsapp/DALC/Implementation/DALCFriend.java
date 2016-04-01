package com.example.jona9892.myfriendsapp.DALC.Implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.jona9892.myfriendsapp.DALC.Abstraction.ICrud;
import com.example.jona9892.myfriendsapp.Model.Implement.Friend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by jona9892 on 01-04-2016.
 */
public class DALCFriend implements ICrud<Friend> {
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + MySQLHelper.TABLE_NAME
            + "(name, phoneNumber, email, address, url, filePath) values (?,?,?,?,?,?)";

    public DALCFriend(Context context) {
        this.context = context;
        MySQLHelper mySQLHelper = new MySQLHelper(this.context);
        this.db = mySQLHelper.getWritableDatabase();
        this.insertStmt = db.compileStatement(INSERT);
    }

    @Override
    public Friend add(Friend item) {
        this.insertStmt.bindString(1, item.getName());
        insertStmt.bindLong(2, item.getPhoneNumber());
        insertStmt.bindString(3, item.getEmail());
        insertStmt.bindString(4, item.getAddress());
        insertStmt.bindString(5, item.getUrl());
        insertStmt.bindString(6, item.getFilePath());

        item.setId((int)insertStmt.executeInsert());
        return item;
    }

    @Override
    public Friend read(int id) {
        List<Friend> list = new ArrayList<Friend>();
        Cursor cursor = db.query(MySQLHelper.TABLE_NAME, new String[] { "id", "name", "phoneNumber", "email", "address", "url", "filePath" },
                "id = " + id, null, null, null, "name asc");
        if (cursor.moveToFirst()) {
            do {
                list.add(new Friend(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list.get(0);
    }

    @Override
    public Collection<Friend> readAll() {
        List<Friend> list = new ArrayList<Friend>();
        Cursor cursor = db.query(MySQLHelper.TABLE_NAME, new String[] { "id", "name", "phoneNumber", "email", "address", "url", "filePath" },
                null, null, null, null, "name asc");
        if (cursor.moveToFirst()) {
            do {
                list.add(new Friend(cursor.getInt(0), cursor.getString(1), cursor.getInt(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6)));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return list;
    }

    @Override
    public void delete(int id) {
        db.delete(MySQLHelper.TABLE_NAME, null, null);
    }

    @Override
    public Friend update(Friend item) {
        ContentValues cv = new ContentValues();
        cv.put("name", item.getName());
        cv.put("phoneNumber", item.getPhoneNumber());
        cv.put("email", item.getEmail());
        cv.put("address", item.getAddress());
        cv.put("url", item.getUrl());
        cv.put("filePath", item.getFilePath());
        db.update(MySQLHelper.TABLE_NAME, cv, "id=" + item.getId(), null);
        return item;
    }
}
