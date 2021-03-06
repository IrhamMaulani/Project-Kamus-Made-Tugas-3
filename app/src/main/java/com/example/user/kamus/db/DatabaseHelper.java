package com.example.user.kamus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.user.kamus.db.DatabaseContract.KamusColumns.ARTI;
import static com.example.user.kamus.db.DatabaseContract.KamusColumns.KALIMAT;
import static com.example.user.kamus.db.DatabaseContract.TABLE_ENG_IND;
import static com.example.user.kamus.db.DatabaseContract.TABLE_IND_ENG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_IND_ENG = "create table "+TABLE_IND_ENG+
            " ("+_ID+" integer primary key autoincrement, " +
            KALIMAT+" text not null, " +
            ARTI+" text not null);";

    public static String CREATE_TABLE_ENG_IND = "create table "+TABLE_ENG_IND+
            " ("+_ID+" integer primary key autoincrement, " +
            KALIMAT +" text not null, " +
            ARTI+" text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IND_ENG);
        db.execSQL(CREATE_TABLE_ENG_IND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_IND_ENG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ENG_IND);
        onCreate(db);
    }
}
