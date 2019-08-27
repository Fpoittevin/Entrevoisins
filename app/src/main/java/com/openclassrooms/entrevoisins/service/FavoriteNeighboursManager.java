package com.openclassrooms.entrevoisins.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighboursManager {

    private Context context;
    private static final String TABLE_FAVORITE_NEIGHBOURS_IDS = "favorite_neighbours_ids";

    public FavoriteNeighboursManager(Context context) {
        this.context = context;
    }

    public boolean isFavorite(Neighbour neighbour) {

        DatabaseManager databaseManager = new DatabaseManager(context);
        SQLiteDatabase db = databaseManager.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FAVORITE_NEIGHBOURS_IDS + " WHERE id = " + neighbour.getId(), null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addToFavorite(Neighbour neighbour) {

        DatabaseManager databaseManager = new DatabaseManager(context);
        SQLiteDatabase db = databaseManager.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("id", neighbour.getId());
        db.insert(TABLE_FAVORITE_NEIGHBOURS_IDS, null, value);
        db.close();
    }

    public void removeToFavorite(Neighbour neighbour) {

        DatabaseManager databaseManager = new DatabaseManager(context);
        SQLiteDatabase db = databaseManager.getWritableDatabase();

        db.delete(TABLE_FAVORITE_NEIGHBOURS_IDS, "id = " + neighbour.getId(), null);
        db.close();
    }

    public List<Neighbour> getFavoriteNeighbours() {

        DatabaseManager databaseManager = new DatabaseManager(context);
        SQLiteDatabase db = databaseManager.getReadableDatabase();

        NeighbourApiService apiService = DI.getNeighbourApiService();

        List<Neighbour> favoriteNeighbours = new ArrayList<>();

        Cursor cursor = db.query(TABLE_FAVORITE_NEIGHBOURS_IDS, new String[]{"id"}, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            favoriteNeighbours.add(apiService.getNeighbour(id));

            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        return favoriteNeighbours;
    }
}
