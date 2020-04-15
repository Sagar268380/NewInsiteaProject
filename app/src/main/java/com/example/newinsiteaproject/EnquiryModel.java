package com.example.newinsiteaproject;

public class EnquiryModel {

    String name,email,subject,numberofchildren,numberofadults,message,country;

    public EnquiryModel(String name, String email, String subject, String numberofchildren, String numberofadults, String message, String country) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.numberofchildren = numberofchildren;
        this.numberofadults = numberofadults;
        this.message = message;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNumberofchildren() {
        return numberofchildren;
    }

    public void setNumberofchildren(String numberofchildren) {
        this.numberofchildren = numberofchildren;
    }

    public String getNumberofadults() {
        return numberofadults;
    }

    public void setNumberofadults(String numberofadults) {
        this.numberofadults = numberofadults;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
