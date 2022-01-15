package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

public class searchRoom extends AppCompatActivity {

    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);
        queue = Volley.newRequestQueue(this);
    }

    public void btnSearch_Click(View view) {
        EditText edtroomnumber = findViewById(R.id.edtroomnumber);
        ListView lst = findViewById(R.id.lstroom);
        String roomnumber = edtroomnumber.getText().toString();
        String url = "http://10.0.2.2/android_project/search_room.php?roomNumber=" + roomnumber;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> rooms = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String sent=obj.getString("roomNumber")+" "+obj.getString("price")+" "+obj.getString("category")+" "+
                                obj.getString("capacity")+" "+obj.getString("status");
                        rooms.add(sent);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                String[] arr = new String[rooms.size()];
                arr = rooms.toArray(arr);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        searchRoom.this, android.R.layout.simple_list_item_1,
                        arr);
                lst.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(searchRoom.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }

    public void showallroom(View view) {
        ListView allroom=findViewById(R.id.allroom);
        String url = "http://10.0.2.2/android_project/getallrooms.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> rooms = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String s="room number :"+obj.getString("roomNumber")+".. price : "+obj.getString("price")+".. category : "+obj.getString("category")+".. capacity : "+obj.getString("capacity")+" ..status"+" "+obj.getString("status");
                        rooms.add(s);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                String[] arr = new String[rooms.size()];
                arr = rooms.toArray(arr);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        searchRoom.this, android.R.layout.simple_list_item_1,
                        arr);
                allroom.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(searchRoom.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);


    }
}