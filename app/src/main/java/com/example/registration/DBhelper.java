package com.example.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {

    public DBhelper( Context context) {
        super(context, "Users.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table user (Id INTEGER PRIMARY KEY ,FullName varchr(255),Email varchar(255),Password varchar(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public Boolean InsertUser(String fullname,String email,String Password){

        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("fullname",fullname);
        contentValues.put("email",email);
        contentValues.put("Password",Password);
        long res=DB.insert("user",null,contentValues);
        if(res==-1) {
            return false;}

        else {return true;}

    }

    public Boolean CheckUserExistence(String email) {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select Email from user where Email=?",new String[] {email});
        if(cursor.getCount()>0){
            return true;
        }
        else return false;

    }

    public Boolean CheckPassword(String email, String password) {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select Email,Password from user where Email=? and Password=?",new String[] {email,password});
        if(cursor.getCount()>0){
            return true;
        }
        else return false;

    }







}
