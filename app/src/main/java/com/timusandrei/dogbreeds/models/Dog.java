package com.timusandrei.dogbreeds.models;

import com.timusandrei.dogbreeds.R;

public final class Dog {

    private final int id;
    private final String name;
    private final String shortDescription;
    private final String longDescription;
    private final String wikiUrl;
    private final int profileImage;
    private final float apartmentLiving;
    private final float familyDog;
    private final float trainable;
    private final float firstTimeDog;

    public Dog() {
        this.id = 1;
        this.name = "Name";
        this.shortDescription = "Description";
        this.longDescription = "Description";
        this.wikiUrl = "https://en.wikipedia.org/wiki/Dog";
        this.profileImage = R.drawable.border_collie;
        this.apartmentLiving = 0f;
        this.familyDog = 0f;
        this.trainable = 0f;
        this.firstTimeDog = 0f;
    }

    public Dog(int id, String name, String shortDescription, String longDescription, String wikiUrl, int profileImage,
               float apartmentLiving, float familyDog, float trainable, float firstTimeDog) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.wikiUrl = wikiUrl;
        this.profileImage = profileImage;
        this.apartmentLiving = apartmentLiving;
        this.familyDog = familyDog;
        this.trainable = trainable;
        this.firstTimeDog = firstTimeDog;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public float getApartmentLiving() {
        return apartmentLiving;
    }

    public float getFamilyDog() {
        return familyDog;
    }

    public float getTrainable() {
        return trainable;
    }

    public float getFirstTimeDog() {
        return firstTimeDog;
    }
}
