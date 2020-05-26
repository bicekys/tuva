package ru.example.tuva.travel.Model;

public class Cities {
    private int id;
    private String card_title;
    private int card_bg;

    //activity_city
    private String city_desc;

    public Cities(int id, String card_title, int card_bg, String city_desc) {
        this.id = id;
        this.card_title = card_title;
        this.card_bg = card_bg;
        this.city_desc = city_desc;
    }

    public int getId() { return id; }

    public String getCard_title() { return card_title; }

    public int getCard_bg() { return card_bg; }

    public String getCity_desc() { return city_desc; }
}

