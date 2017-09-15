package com.example.brijesh.authsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.provider.Settings;

/**
 * Created by Brijesh on 9/15/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String database_name="user.db";
    SQLiteDatabase db;

    public DatabaseHandler(Context context)
    {
        super(context,database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user (username text primary key,password text,number text)");
        this.db=sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists user");
        onCreate(sqLiteDatabase);
    }

    public void insertValues(UserPojo userPojo)
    {
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",userPojo.getUser_name());
        contentValues.put("password",userPojo.getPassword());
        contentValues.put("number",userPojo.getNo());

        long l=db.insert("user",null,contentValues);
        System.out.println("valuesPrint"+l);
    }

    public String searchPasswordFromDatabase(String uName)
    {
        db=this.getReadableDatabase();
        String returned_uName,returned_password="not found";
        Cursor cursor=db.rawQuery("select username,password from user",null);
        if (cursor.moveToFirst())
        {
            do {
                returned_uName=cursor.getString(0);
                if (returned_uName.equals(uName))
                {
                    returned_password=cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());
        }
      return returned_password;
    }
    public String getUserData(String uName)
    {
        db=this.getReadableDatabase();
        String returned_uName,returned_contact="not found";
        Cursor cursor=db.rawQuery("select username,number from user",null);
        if (cursor.moveToFirst())
        {
            do {
                returned_uName=cursor.getString(0);
                if (returned_uName.equals(uName))
                {
                    returned_contact=cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());
        }
        return returned_contact;
    }
}
