package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import model.CaptionedImagesAdapter;
import model.Pizza;

public class rest_recycleview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_recycleview);

        RecyclerView recycler = findViewById(R.id.pizza_recycler);


        String[] captions = new String[Pizza.pizzas.length];
        int[] ids = new int[Pizza.pizzas.length];

        for(int i = 0; i<captions.length;i++){
            captions[i] = Pizza.pizzas[i].getName();
            ids[i] = Pizza.pizzas[i].getImageID();
        }
        recycler.setLayoutManager(new LinearLayoutManager(this));
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(captions, ids);
        recycler.setAdapter(adapter);
    }
    public void onClickeat(View view){
        Intent intent = new Intent(this ,confEat.class);
        startActivity(intent);

    }



}