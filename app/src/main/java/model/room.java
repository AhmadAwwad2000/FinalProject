package model;

import com.example.finalproject.R;

public class room {
    private int roomNumber;

    private String name;


    public static final model.room[] rooms = {
            new model.room("Room 10 ", R.drawable.img_8),
            new model.room("Room 11", R.drawable.img_9),
            new model.room("Room 12", R.drawable.img_10),
            new model.room("Room 12", R.drawable.img_11),
            new model.room("Room 12", R.drawable.img_12),
            new model.room("Room 12", R.drawable.img_13),
            new model.room("Room 12", R.drawable.img_14),
            new model.room("Room 12", R.drawable.img_15),
            new model.room("Room 12", R.drawable.img_8),
            new model.room("Room 12", R.drawable.img_11),
            new model.room("Room 12", R.drawable.img_14),
            new model.room("Room 12", R.drawable.img_15),
            new model.room("Room 12", R.drawable.img_8),
            new model.room("Room 12", R.drawable.img_11),
            new model.room("Room 12", R.drawable.img_14),
            new model.room("Room 12", R.drawable.img_15),
            new model.room("Room 12", R.drawable.img_8),
            new model.room("Room 12", R.drawable.img_11),
            new model.room("Room 12", R.drawable.img_14),
            new model.room("Room 12", R.drawable.img_15),
            new model.room("Room 12", R.drawable.img_8),
            new model.room("Room 12", R.drawable.img_11),

    };
    private room(String name,int roomNumber)
    {
        this.name = name;
        this.roomNumber = roomNumber;
    }


    public String getName(){return name;}

    public int getroomNumber(){return roomNumber;}

}

