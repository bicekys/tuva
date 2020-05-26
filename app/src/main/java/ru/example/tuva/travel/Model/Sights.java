package ru.example.tuva.travel.Model;

public class Sights {
    private int id;
    private String sights_title, sights_desc, sights_details, sights_fullDesc;
    private int sights_bg;

    public Sights(int id, String sights_title, String sights_desc, int sights_bg, String sights_details, String sights_fullDesc) {
        this.id = id;
        this.sights_title = sights_title;
        this.sights_desc = sights_desc;
        this.sights_bg = sights_bg;
        this.sights_details = sights_details;
        this.sights_fullDesc = sights_fullDesc;
    }

    public int getId() { return id; }

    public String getSights_title() { return sights_title; }

    public String getSights_desc() { return sights_desc; }

    public int getSights_bg() { return sights_bg; }

    public String getSights_details() { return sights_details; }

    public String getSights_fullDesc() { return sights_fullDesc; }
}
