package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class FavoriteServiceTest {

    private NeighbourApiService mNeighbourApiService;
    private FavoriteNeighboursManager mFavoriteNeighboursManager;

    @Before
    public void setup() {

        mNeighbourApiService = DI.getNewInstanceApiService();

        FavoriteNeighboursStorage favoriteNeighboursStorage = mock(FavoriteNeighboursStorage.class);
        mFavoriteNeighboursManager = new FavoriteNeighboursManager(favoriteNeighboursStorage);

        List<Neighbour> fakeStorage = new ArrayList<>();
        fakeStorage.add(mNeighbourApiService.getNeighbour(1));
        fakeStorage.add(mNeighbourApiService.getNeighbour(2));

        when(favoriteNeighboursStorage.getListFromPreferences()).thenReturn(fakeStorage);
    }

    @Test
    public void addNeighbourToFavorite() {

        Neighbour neighbour = mNeighbourApiService.getNeighbour(3);
        mFavoriteNeighboursManager.addToFavorite(neighbour);

        assertTrue(mFavoriteNeighboursManager.getFavoriteNeighbours().contains(neighbour));
    }

    @Test
    public void removeNeighbourToFavorite() {
        Neighbour neighbour = mNeighbourApiService.getNeighbour(1);
        mFavoriteNeighboursManager.removeToFavorite(neighbour);

        assertFalse(mFavoriteNeighboursManager.getFavoriteNeighbours().contains(neighbour));
    }

    @Test
    public void neighbourIsNotFavorite() {
        Neighbour neighbour = mNeighbourApiService.getNeighbour(4);
        assertFalse(mFavoriteNeighboursManager.isFavorite(neighbour));
    }
}