package ru.example.tuva.travel.Model;

public class Experiences {
    private int exp_id;
    private int exp_image;

    public Experiences(int exp_id, int exp_image) {
        this.exp_id = exp_id;
        this.exp_image = exp_image;
    }

    public int getExp_id() { return exp_id; }

    public int getExp_image() { return exp_image; }

}
