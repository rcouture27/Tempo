package com.example.ryan.temporaryname;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Login_DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Login_android.db";
    public static final String TABLE_NAME = "login_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "EMAIL";
    public static final String COL3 = "PASSWORD";


    public Login_DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT NOT NULL, PASSWORD TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String input_email, String input_pass, String registered_email, String registered_pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (!input_email.equals(registered_email)) {
            contentValues.put(COL2, input_email);
            contentValues.put(COL3, input_pass);
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result < 0) {
                return false;
            } else {
                return true;
            }
        } // end if
        return false;

}// end insertData

    public Cursor getEmail(String input_email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res_email = db.rawQuery("SELECT * FROM " + TABLE_NAME + "  WHERE email = " + DatabaseUtils.sqlEscapeString(String.valueOf(input_email)), null);
        return res_email;
    }

    public Cursor getPass(String input_pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res_pass = db.rawQuery("SELECT * FROM login_table WHERE password = " + DatabaseUtils.sqlEscapeString(String.valueOf(input_pass)), null);
        return res_pass;
    }
}
