package com.example.newinsiteaproject;

public class RecycleViewAdapterModelTwo {

    int hotelImage;
    String hotelName;
    String  price;
     String city;


    public RecycleViewAdapterModelTwo(int hotelImage, String hotelName, String price, String city) {
        this.hotelImage = hotelImage;
        this.hotelName = hotelName;
        this.price = price;
        this.city = city;
    }

    public int getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(int hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
