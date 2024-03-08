package com.zybooks.contactinfosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class tableHelper extends SQLiteOpenHelper {
    public tableHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, contactNumber TEXT, age TEXT, faveColor TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertData(String name, String contactNumber, String age, String faveColor){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contactNumber", contactNumber);
        contentValues.put("age", age);
        contentValues.put("faveColor", faveColor);
        long result = DB.insert("Userdetails", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateData(String name, String contactNumber, String age, String faveColor){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contactNumber", contactNumber);
        contentValues.put("age", age);
        contentValues.put("faveColor", faveColor);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name}); //a cursor shows up when you select a row
        if(cursor.getCount()>0){
        long result = DB.update("Userdetails", contentValues, "name=?", new String[] {name});
        if(result == -1){
            return false;
        }else{
            return true;
        }
        }else{
            return false;
        }
    }

    public Boolean deleteData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name}); //a cursor shows up when you select a row
        if(cursor.getCount()>0){
            long result = DB.delete("Userdetails", "name=?", new String[] {name});
            if(result == -1){
                return false;
            }else{
                return true;
            }
            }else{
                return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null); //a cursor shows up when you select a row
        return cursor;
    }
}
