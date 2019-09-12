package com.openclassrooms.entrevoisins.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FavoriteNeighboursStorage {

    private final Context mContext;

    public FavoriteNeighboursStorage(Context context) {
        mContext = context;
    }

    /**
     * Takes the serialized list of favorite ids in the preferences
     * and returns the list of favorite neighbours
     *
     * @return favoriteNeighbours
     */
    public List<Neighbour> getListFromPreferences() {

        SharedPreferences preferences = mContext.getSharedPreferences("EntreVoisinsPreferences", MODE_PRIVATE);
        String favoriteNeighboursIdsSerialized = preferences.getString("favoriteNeighboursIdsSerialized",null);

        List<Neighbour> favoriteNeighbours = new ArrayList<>();

        if(favoriteNeighboursIdsSerialized != null) {
            favoriteNeighboursIdsSerialized = favoriteNeighboursIdsSerialized.substring(1, favoriteNeighboursIdsSerialized.length() - 1);

            if (favoriteNeighboursIdsSerialized.length() != 0) {

                String[] ids = favoriteNeighboursIdsSerialized.split(",");

                for (String s : ids) {
                    int id = Integer.parseInt(s.trim());

                    NeighbourApiService neighbourApiService = DI.getNeighbourApiService();
                    Neighbour neighbour = neighbourApiService.getNeighbour(id);

                    favoriteNeighbours.add(neighbour);
                }
            }
        }

        return favoriteNeighbours;
    }

    /**
     * Extracts ids from the favorites list,
     * serializes them,
     * and inserts them into preferences
     *
     * @param favoriteNeighbours
     */
    public void updateListInPreferences(List<Neighbour> favoriteNeighbours) {

        SharedPreferences preferences = mContext.getSharedPreferences("EntreVoisinsPreferences", MODE_PRIVATE);

        List<Integer> FavoriteNeighboursIds = new ArrayList<>();

        for (int i = 0; i < favoriteNeighbours.size(); i++) {
            FavoriteNeighboursIds.add(favoriteNeighbours.get(i).getId());
        }

        preferences.edit().putString("favoriteNeighboursIdsSerialized", FavoriteNeighboursIds.toString()).apply();
    }
}
