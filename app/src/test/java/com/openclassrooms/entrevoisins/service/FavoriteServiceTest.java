package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FavoriteServiceTest {

    private NeighbourApiService mNeighbourApiService;

    @Before
    public void setup() {

        mNeighbourApiService = DI.getNewInstanceApiService();
    }

    @Test
    public void addNeighbourToFavorite() {

        Neighbour neighbour = mNeighbourApiService.getNeighbour(3);

        if(!neighbour.isFavorite()) {
            mNeighbourApiService.changeFavoriteStatus(neighbour);
        }

        assertTrue(mNeighbourApiService.getFavorites().contains(neighbour));
    }

    @Test
    public void removeNeighbourToFavorite() {
        Neighbour neighbour = mNeighbourApiService.getNeighbour(1);

        if(neighbour.isFavorite()) {
            mNeighbourApiService.changeFavoriteStatus(neighbour);
        }

        assertFalse(mNeighbourApiService.getFavorites().contains(neighbour));
    }

    @Test
    public void getFavoriteNeighbours() {

        List<Neighbour> neighboursToAddToFavorites = new ArrayList<>();
        neighboursToAddToFavorites.add(mNeighbourApiService.getNeighbour(1));
        neighboursToAddToFavorites.add(mNeighbourApiService.getNeighbour(2));

        for(int i = 0; i < neighboursToAddToFavorites.size(); i++) {

            if(!neighboursToAddToFavorites.get(i).isFavorite()) {
                mNeighbourApiService.changeFavoriteStatus(neighboursToAddToFavorites.get(i));
            }
        }

        int numberOfFavorites = 0;

        for(int i = 0; i < mNeighbourApiService.getNeighbours().size(); i++) {

            if(mNeighbourApiService.getNeighbours().get(i).isFavorite()){
                numberOfFavorites++;
            }
        }

        assertEquals(numberOfFavorites, mNeighbourApiService.getFavorites().size());
    }
}