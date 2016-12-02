package com.blast.inapp;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.Drawable;

/**
 * Created by owner on 25/9/2016.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String role;
    private String description;
    private Drawable image;

    public Person(){}

    public Person(String firstName, String lastName, String role, String description, Drawable image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.description = description;
        this.image = image;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
