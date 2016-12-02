package com.blast.inapp;

/**
 * Created by owner on 6/11/2016.
 */

public class Employer {
    private String name, description;
    private long dateFounded;

    public Employer(){}

    public Employer(String name, String description, long dateFounded) {
        this.name = name;
        this.description = description;
        this.dateFounded = dateFounded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateFounded(){
        return Util.formatDate(dateFounded);
    }

    void setDateFounded(long dateFounded) {
        this.dateFounded = dateFounded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
