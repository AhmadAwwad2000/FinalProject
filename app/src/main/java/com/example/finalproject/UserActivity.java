package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import model.select_from_spinner;
import model.select_from_spinner_user;

public class UserActivity extends AppCompatActivity {
private TextView textview;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        textview=findViewById(R.id.textview);
        spinner=findViewById(R.id.spinner);
        Intent intent=getIntent();
        String uname=intent.getStringExtra("username");
        textview.setText("WELCOME "+uname);
        fillspinner();
    }
    public void fillspinner(){
        select_from_spinner_user type=new select_from_spinner_user();
        String [] type1=type.cat();
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this , android.R.layout.simple_spinner_item,type1);
        spinner.setAdapter(adapter);
    }
    public void spinneronclick(View view) {
        if(spinner.getSelectedItem().toString().equals("Search room")){
            Intent intent = new Intent(this ,searchRoom.class);
            startActivity(intent);}
        else if(spinner.getSelectedItem().toString().equals("Swimming")){
            Intent intent = new Intent(this ,swimming.class);
            startActivity(intent);}
        else if(spinner.getSelectedItem().toString().equals("Room reservations")){
            Intent intent = new Intent(this ,reservations.class);
            startActivity(intent);}
        else if(spinner.getSelectedItem().toString().equals("eat")){
            Intent intent = new Intent(this ,rest_recycleview.class);
            startActivity(intent);}
    }
}