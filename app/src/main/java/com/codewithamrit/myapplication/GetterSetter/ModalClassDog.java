package com.codewithamrit.myapplication.GetterSetter;

import android.graphics.Bitmap;

public class ModalClassDog {
    public  String id;
public String dogName;
public String dogAge;
public String dogGender;
public Bitmap dogImage;
public String dogBreed;
public String dogLocation;
public String dogMessage;
public String imageUrl;
    public ModalClassDog() {
    }
    public ModalClassDog(String dogName, String dogAge, String dogGender, Bitmap dogImage, String dogBreed, String dogLocation, String dogMessage, String imageUrl) {
        this.dogName = dogName;
        this.dogAge = dogAge;
        this.dogGender = dogGender;
        this.dogImage = dogImage;
        this.dogBreed = dogBreed;
        this.dogLocation = dogLocation;
        this.dogMessage = dogMessage;
        this.imageUrl=imageUrl;
    }

    public ModalClassDog(String id, String dogName, String dogAge, String dogGender, String dogBreed, String dogLocation, String dogMessage, String imageUrl) {
        this.id = id;
        this.dogName = dogName;
        this.dogAge = dogAge;
        this.dogGender = dogGender;
        this.dogBreed = dogBreed;
        this.dogLocation = dogLocation;
        this.dogMessage = dogMessage;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogAge() {
        return dogAge;
    }

    public void setDogAge(String dogAge) {
        this.dogAge = dogAge;
    }

    public String getDogGender() {
        return dogGender;
    }

    public void setDogGender(String dogGender) {
        this.dogGender = dogGender;
    }

    public Bitmap getDogImage() {
        return dogImage;
    }

    public void setDogImage(Bitmap dogImage) {
        this.dogImage = dogImage;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getDogLocation() {
        return dogLocation;
    }

    public void setDogLocation(String dogLocation) {
        this.dogLocation = dogLocation;
    }

    public String getDogMessage() {
        return dogMessage;
    }

    public void setDogMessage(String dogMessage) {
        this.dogMessage = dogMessage;
    }
}