package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    static String LOREM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint Pierre du Mont", 5, "+33 6 86 57 90 14", "www.facebook.fr/caroline", LOREM),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "Saint Pierre du Mont", 1, "+33 6 86 57 90 13", "www.facebook.fr/jack", LOREM),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", "Saint Pierre du Mont", 2, "+33 6 86 57 90 12", "www.facebook.fr/chloe", LOREM),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", "Saint Pierre du Mont", 3, "+33 6 86 57 90 11", "www.facebook.fr/vincent", LOREM),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", "Saint Pierre du Mont", 4, "+33 6 86 57 90 10", "www.facebook.fr/elodie", LOREM),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", "Saint Pierre du Mont", 5, "+33 6 86 57 90 11", "www.facebook.fr/sylvain", LOREM),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", "Saint Pierre du Mont", 1, "+33 6 86 57 90 12", "www.facebook.fr/laeticia", LOREM),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", "Saint Pierre du Mont", 2, "+33 6 86 57 90 13", "www.facebook.fr/dan", LOREM),
            new Neighbour(9, "Joseph", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint Pierre du Mont", 3, "+33 6 86 57 90 14", "www.facebook.fr/joseph", LOREM),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", "Saint Pierre du Mont", 4, "+33 6 86 57 90 15", "www.facebook.fr/emma", LOREM),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", "Saint Pierre du Mont", 5, "+33 6 86 57 90 14", "www.facebook.fr/patrick", LOREM),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", "Saint Pierre du Mont", 5, "+33 6 86 57 90 13a", "www.facebook.fr/ludovic", LOREM)
    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);
    }
}