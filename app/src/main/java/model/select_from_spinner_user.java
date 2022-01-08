package model;

import java.util.ArrayList;

public class select_from_spinner_user {
    private ArrayList<String> list=new ArrayList<>();

    public select_from_spinner_user() {

    }
    public String [] cat(){
        String [] type=new String[]{"Search room","Room reservations","Swimming"};
        return type;
    }
}
