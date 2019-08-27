package com.openclassrooms.entrevoisins.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EntreVoisins.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FAVORITE_NEIGHBOURS_IDS = "favorite_neighbours_ids";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_FAVORITE_NEIGHBOURS_IDS + "(id integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE_NEIGHBOURS_IDS);
        onCreate(db);
    }
}
