package com.example.ryan.temporaryname;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "Login_android.db";
        public static final String TABLE_NAME = "login_table";
        public static final String COL1 = "ID";
        public static final String COL2 = "EMAIL";
        public static final String COL3 = "PASSWORD";


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
            //SQLiteDatabase db = this.getWritableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, EMAIL TEXT, PASSWORD TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        public boolean insertData(String email, String password) {

            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(COL2, email);
            contentValues.put(COL3, password);
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }

        public Cursor getData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res_email = db.rawQuery("SELECT * FROM login_table WHERE email LIKE 'rc%' ", null);
            Cursor res_pass = db.rawQuery("SELECT * FROM login_table WHERE password LIKE 'ryan%' ", null);
            return res_pass;
        }
    }
