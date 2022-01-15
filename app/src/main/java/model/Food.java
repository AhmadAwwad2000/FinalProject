package model;

import com.example.finalproject.R;

public class Food {

    private int imageID;

    public static final Food[] food = {
            new Food(R.drawable.img),
            new Food( R.drawable.img_1),
            new Food( R.drawable.img_6),
            new Food(  R.drawable.img_2),
            new Food( R.drawable.arabic),

    };
    private Food(int imageID){

        this.imageID = imageID;
    }

    public int getImageID(){return imageID;}
}