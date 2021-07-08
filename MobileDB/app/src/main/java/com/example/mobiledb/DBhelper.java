package com.example.mobiledb;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME="mobiledb";

    public DBhelper(Context context) {
        super(context, "mobiledb", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create table users(username TEXT primary key, password TEXT)");
        Mydb.execSQL("create table profile(username TEXT,address TEXT,educational_qualification TEXT,sslc NUMBER,hsc NUMBER,ug NUMBER,pg NUMBER,FOREIGN KEY (username) REFERENCES users(username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int i,int i1) {
        Mydb.execSQL("drop table if exists users");
        Mydb.execSQL("drop table if exists profile");

    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long results=Mydb.insert("users",null,contentValues);
        if (results==-1) {
            return false;
        }
        else{
            return true;
        }
    }


    public Boolean insertprofile(String user,String addr,String eduq,String sslcm,String hscm,String ugm,String pgm){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",user);
        //contentValues.put("dob",dob);
        contentValues.put("address",addr);
        contentValues.put("educational_qualification",eduq);
        contentValues.put("sslc",sslcm);
        contentValues.put("hsc",hscm);
        contentValues.put("ug",ugm);
        contentValues.put("pg",pgm);

        long results=Mydb.insert("profile",null,contentValues);
        if (results==-1) {
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(String sql){ SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public Boolean checkusername(String username){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username ,String password){
        SQLiteDatabase Mydb=this.getWritableDatabase();
        Cursor cursor= Mydb.rawQuery("select * from users where username = ? and password = ?",new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


}

