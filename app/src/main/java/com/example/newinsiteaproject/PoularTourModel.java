package com.example.newinsiteaproject;

public class PoularTourModel {

    int image;
    String places,tour1,tour2,tour3,munnar;

    public PoularTourModel(int image, String tour1, String tour2, String tour3, String munnar,String places) {
        this.image = image;
        this.tour1 = tour1;
        this.tour2 = tour2;
        this.tour3 = tour3;
        this.munnar = munnar;
        this.places=places;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTour1() {
        return tour1;
    }

    public void setTour1(String tour1) {
        this.tour1 = tour1;
    }

    public String getTour2() {
        return tour2;
    }

    public void setTour2(String tour2) {
        this.tour2 = tour2;
    }

    public String getTour3() {
        return tour3;
    }

    public void setTour3(String tour3) {
        this.tour3 = tour3;
    }

    public String getMunnar() {
        return munnar;
    }

    public void setMunnar(String munnar) {
        this.munnar = munnar;
    }
}
