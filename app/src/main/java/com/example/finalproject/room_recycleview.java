package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



import model.cardroom;

import model.room;

public class room_recycleview extends AppCompatActivity {
    private RequestQueue queue;
    String [] roomdb;

    private RecyclerView recycler;
String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_recycleview);
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        recycler= findViewById(R.id.room_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        queue = Volley.newRequestQueue(this);
        roomdb  =new String[10];
        getdatafromdatabase();

        int[] ids = new int[room.rooms.length];
        for(int i = 0; i<ids.length;i++){
            ids[i] = room.rooms[i].getroomNumber();
        }
    }
    public void getdatafromdatabase(){
        String url = "http://10.0.2.2/android_project/getallrooms.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String [] roomm=new String[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String s=obj.get("roomNumber").toString();

                        roomm[i] = "Room Number: "+obj.get("roomNumber").toString()+ ","
                                + " Price:  "+ obj.get("price").toString() + ","
                                + "Category:  "+obj.getString("category") + "," + " capacity: "
                                +obj.get("capacity").toString()+" Status : "+obj.getString("status");


                        Log.d("-------------------", roomm[i]);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                int[] ids = new int[room.rooms.length];

                for(int i = 0; i<ids.length;i++){

                    ids[i] = room.rooms[i].getroomNumber();
                }

                cardroom adapter = new cardroom(roomm, ids);
                recycler.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(room_recycleview.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }
    public void onClickroom(View view){
        Intent intent = new Intent(this ,reservations.class);
        intent.putExtra("username",username);
        startActivity(intent);

    }
}