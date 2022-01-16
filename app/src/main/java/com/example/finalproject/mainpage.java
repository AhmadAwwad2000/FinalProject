package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import model.select_from_spinner;

public class mainpage extends AppCompatActivity {
private TextView textview;
private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        textview=findViewById(R.id.textview);
        spinner=findViewById(R.id.spinner);
        Intent intent=getIntent();
        String uname=intent.getStringExtra("username");
        textview.setText("WELCOME "+uname);
        fillspinner();
    }
    public void fillspinner(){
        select_from_spinner type=new select_from_spinner();
        String [] type1=type.cat();
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this , android.R.layout.simple_spinner_item,type1);
        spinner.setAdapter(adapter);
    }

    public void spinneronclick(View view) {
        if(spinner.getSelectedItem().toString().equals("Search room")){
            Intent intent = new Intent(this ,searchRoom.class);
            startActivity(intent);}
        else if(spinner.getSelectedItem().toString().equals("Add room")){
            Intent intent2 = new Intent(this ,AddRoom.class);
            startActivity(intent2);}
        else if(spinner.getSelectedItem().toString().equals("View user")){
            Intent intent2 = new Intent(this ,viewuser.class);
            startActivity(intent2);}
        else if(spinner.getSelectedItem().toString().equals("Remove room")){
            Intent intent2 = new Intent(this ,deleteroom.class);
            startActivity(intent2);}
        else if(spinner.getSelectedItem().toString().equals("Update room")){
            Intent intent2 = new Intent(this ,updateroom.class);
            startActivity(intent2);}
        else if(spinner.getSelectedItem().toString().equals("View all booked rooms")){
            Intent intent2 = new Intent(this ,reserved_room.class);
            startActivity(intent2);}
    }


}