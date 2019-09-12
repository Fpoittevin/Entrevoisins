package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.List;

public class FavoriteNeighboursManager {

    private FavoriteNeighboursStorage mFavoriteNeighboursStorage;
    private List<Neighbour> favoriteNeighbours;

    public FavoriteNeighboursManager(FavoriteNeighboursStorage favoriteNeighboursStorage) {

        mFavoriteNeighboursStorage = favoriteNeighboursStorage;
    }

    /**
     * Checks if a neighbour is a favorite
     * @param neighbour
     * @return boolean
     */
    public boolean isFavorite(Neighbour neighbour) {

        favoriteNeighbours = mFavoriteNeighboursStorage.getListFromPreferences();
        return favoriteNeighbours.contains(neighbour);
    }

    /**
     * Adds a neighbour to the favorites list
     * @param neighbour
     */
    public void addToFavorite(Neighbour neighbour) {

        favoriteNeighbours = mFavoriteNeighboursStorage.getListFromPreferences();
        favoriteNeighbours.add(neighbour);
        mFavoriteNeighboursStorage.updateListInPreferences(favoriteNeighbours);
    }

    /**
     * Removes a neighbour from the favorites list
     * @param neighbour
     */
    public void removeToFavorite(Neighbour neighbour) {

        favoriteNeighbours = mFavoriteNeighboursStorage.getListFromPreferences();
        favoriteNeighbours.remove(neighbour);
        mFavoriteNeighboursStorage.updateListInPreferences(favoriteNeighbours);
    }

    /**
     * Returns the list of favorite neighbour
     * @return favoriteNeighbours
     */
    public List<Neighbour> getFavoriteNeighbours() {

        favoriteNeighbours = mFavoriteNeighboursStorage.getListFromPreferences();
        return favoriteNeighbours;
    }
}