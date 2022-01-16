package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class reserved_room extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved_room);
        queue = Volley.newRequestQueue(this);
        filllist();
    }

    public void filllist() {
        ListView lstroomres=findViewById(R.id.lstroomres);
        String url = "http://10.0.2.2/android_project/getallroomsres.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> rooms = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String s=" user name : "+obj.getString("username")+" .... room number : "+obj.getString("roomNumber");
                        rooms.add(s);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                String[] arr = new String[rooms.size()];
                arr = rooms.toArray(arr);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        reserved_room.this, android.R.layout.simple_list_item_1,
                        arr);
                lstroomres.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(reserved_room.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);



    }

    public void prev(View view) {
        Intent intent = new Intent(this ,mainpage.class);
        startActivity(intent);
    }
}