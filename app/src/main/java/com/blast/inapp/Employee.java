package com.blast.inapp;

import java.util.Calendar;

/**
 * Created by owner on 6/11/2016.
 */

class Employee {
    private String firstName, lastName, jobDescription, employerName, employerDescription;
    private long dateOfBirth, dateOfEmployment, employerFoundedDate;
    private int employerID;

    Employee(){}

    Employee(String firstName, String lastName, String jobDescription, int employerID, long dateOfBirth, long dateOfEmployment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobDescription = jobDescription;
        this.employerID = employerID;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
    }

    void setEmployerName(String employerName){
        this.employerName = employerName;
    }

    String getEmployerName(){return employerName;}

    void setEmployerDescription(String employerDescription){
        this.employerDescription = employerDescription;
    }

    String getEmployerDescription(){return employerDescription;}

    void setEmployerFoundedDate(long employerFoundedDate){
        this.employerFoundedDate = employerFoundedDate;
    }

    long getEmployerFoundedDate(){return employerFoundedDate; }

    long getDateOfEmployment() {
        return dateOfEmployment;
    }

    void setDateOfEmployment(long dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    long getDateOfBirth() {
        return dateOfBirth;
    }

    void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    int getEmployerID() {
        return employerID;
    }

    void setEmployerID(int employerID) {
        this.employerID = employerID;
    }

    String getJobDescription() {
        return jobDescription;
    }

    void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    String getName(){
        return lastName + " " + firstName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
