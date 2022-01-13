package model;

import com.example.finalproject.R;

public class Pizza {
    private String name;
    private int imageID;

    public static final Pizza[] pizzas = {
            new Pizza("Diavolo", R.drawable.img_6),
            new Pizza("Funghi", R.drawable.img_7),
            new Pizza("Diavolo2", R.drawable.img_6),
            new Pizza("Funghi2",  R.drawable.img_7),
            new Pizza("Diavolo3", R.drawable.img_6),
            new Pizza("Funghi3",  R.drawable.img_7),
    };
    private Pizza(String name, int imageID){
        this.name = name;
        this.imageID = imageID;
    }
    public String getName(){return name;}
    public int getImageID(){return imageID;}
}