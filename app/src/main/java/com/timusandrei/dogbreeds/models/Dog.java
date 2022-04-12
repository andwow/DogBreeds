package com.timusandrei.dogbreeds.models;

import android.os.Parcelable;

import com.timusandrei.dogbreeds.R;

import java.io.Serializable;

public class Dog {

    private int id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String wikiUrl;
    private int profileImage;
    private float apartmentLiving;
    private float familyDog;
    private float trainable;
    private float firstTimeDog;

    public Dog() {
        this.id = 1;
        this.name = "Name";
        this.shortDescription = "Description";
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public float getApartmentLiving() {
        return apartmentLiving;
    }

    public void setApartmentLiving(float apartmentLiving) {
        this.apartmentLiving = apartmentLiving;
    }

    public float getFamilyDog() {
        return familyDog;
    }

    public void setFamilyDog(float familyDog) {
        this.familyDog = familyDog;
    }

    public float getTrainable() {
        return trainable;
    }

    public void setTrainable(float trainable) {
        this.trainable = trainable;
    }

    public float getFirstTimeDog() {
        return firstTimeDog;
    }

    public void setFirstTimeDog(float firstTimeDog) {
        this.firstTimeDog = firstTimeDog;
    }
}
