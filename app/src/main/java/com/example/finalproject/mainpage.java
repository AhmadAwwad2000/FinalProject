package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import model.select_from_spinner;

public class mainpage extends AppCompatActivity {
private TextView textview;
private Spinner spinner;
String uname="";
private EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        textview=findViewById(R.id.textview);
        spinner=findViewById(R.id.spinner);
        username=findViewById(R.id.username);
        Intent intent=getIntent();
       uname=intent.getStringExtra("username");
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
            intent2.putExtra("username",uname);
            startActivity(intent2);}
        else if(spinner.getSelectedItem().toString().equals("update informations")){
            Intent intent2 = new Intent(this ,updateInfo.class);
            intent2.putExtra("username",uname);
            startActivity(intent2);}
        else if(spinner.getSelectedItem().toString().equals("update information for specific user")){
            Intent intent2 = new Intent(this ,updateInformationFromAdmin.class);
            startActivity(intent2);}

    }


    public void createaccount(View view) {
        Intent intent2 = new Intent(this ,CreateAccount.class);
        startActivity(intent2);

    }

    public void res(View view) {
        String user=username.getText().toString();
        Intent intent2 = new Intent(this ,room_recycleview.class);
        intent2.putExtra("username",user);
        startActivity(intent2);

    }

    public void logoutt(View view) {
        Intent intent = new Intent(this ,login.class);
        startActivity(intent);
    }
}