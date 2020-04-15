package com.example.newinsiteaproject;

public class AdapterModel {
    int imageid;
    String price;
    String days,placeName;

    public AdapterModel() {
    }

    public AdapterModel(int imageid, String price, String days, String placeName) {
        this.imageid = imageid;
        this.price = price;
        this.days = days;
        this.placeName = placeName;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
