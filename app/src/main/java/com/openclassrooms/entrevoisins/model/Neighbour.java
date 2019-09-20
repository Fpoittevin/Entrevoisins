package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Address */
    private String address;

    /** Distance */
    private float distance;

    /** Phone number */
    private String phoneNumber;

    /** Facebook account */
    private String facebookAccount;

    /** Description */
    private String description;



    /** Favorite */
    private boolean isFavorite;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param address
     * @param distance
     * @param phoneNumber
     * @param facebookAccount
     * @param description
     */
    public Neighbour(Integer id, String name, String avatarUrl, String address, float distance, String phoneNumber, String facebookAccount, String description) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.address = address;
        this.distance = distance;
        this.phoneNumber = phoneNumber;
        this.facebookAccount = facebookAccount;
        this.description = description;
        this.isFavorite = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public float getDistance() { return distance; }

    public void setDistance(float distance) { this.distance = distance; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getFacebookAccount() { return facebookAccount; }

    public void setFacebookAccount(String facebookAccount) { this.facebookAccount = facebookAccount; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isFavorite() { return isFavorite; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
