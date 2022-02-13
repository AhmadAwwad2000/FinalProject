package model;

import java.util.ArrayList;

public class select_from_spinner {
private ArrayList<String> list=new ArrayList<>();

    public select_from_spinner() {

    }
    public String [] cat(){
        String [] type=new String[]{"Add room","Search room","View user","Remove room","Update room","View all booked rooms","update informations",
                "update information for specific user"};
        return type;
    }
}
