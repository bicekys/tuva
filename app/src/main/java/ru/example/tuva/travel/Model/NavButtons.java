package ru.example.tuva.travel.Model;

public class NavButtons {
    private int id;
    private int btn_image;
    private String btn_name;
    private String btn_city_title;
    private int btn_city_image;

    public NavButtons(int id, int btn_image, String btn_name, String btn_city_title, int btn_city_image) {
        this.id = id;
        this.btn_image = btn_image;
        this.btn_name = btn_name;
        this.btn_city_title = btn_city_title;
        this.btn_city_image = btn_city_image;
    }

    public int getId() {
        return id;
    }

    public int getBtn_image() {
        return btn_image;
    }

    public String getBtn_name() { return btn_name; }

    public String getBtn_city_title() { return btn_city_title; }

    public int getBtn_city_image() { return btn_city_image; }
}
