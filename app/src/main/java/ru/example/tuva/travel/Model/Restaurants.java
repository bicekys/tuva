package ru.example.tuva.travel.Model;

public class Restaurants {
    private int id;
    private String restaurants_title, restaurants_desc, restaurants_details, restaurants_fullDesc;
    int restaurants_bg;

    public Restaurants(int id, String restaurants_title, String restaurants_desc, int restaurants_bg, String restaurants_details, String restaurants_fullDesc) {
        this.id = id;
        this.restaurants_title = restaurants_title;
        this.restaurants_desc = restaurants_desc;
        this.restaurants_bg = restaurants_bg;
        this.restaurants_details = restaurants_details;
        this.restaurants_fullDesc = restaurants_fullDesc;
    }

    public int getId() { return id; }

    public String getRestaurants_title() { return restaurants_title; }

    public String getRestaurants_desc() { return restaurants_desc; }

    public int getRestaurants_bg() { return restaurants_bg; }

    public String getRestaurants_details() { return restaurants_details; }

    public String getRestaurants_fullDesc() { return restaurants_fullDesc; }
}
