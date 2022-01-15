package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import model.CaptionedImagesAdapter;
import model.Food;

public class rest_recycleview extends AppCompatActivity {

    private RequestQueue queue;

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_recycleview);
        recycler= findViewById(R.id.pizza_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        queue = Volley.newRequestQueue(this);
        getdatafromdatabase();


    }
    public void onClickeat(View view){
        Intent intent = new Intent(this ,confEat.class);
        startActivity(intent);
    }
    public void getdatafromdatabase(){
        String url = "http://10.0.2.2/android_project/getallfood.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

               String [] foodd=new String[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        foodd[i]=obj.getString("foodname");
                        Log.d("-------------------", foodd[i]);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                int[] ids = new int[Food.food.length];

                for(int i = 0; i<ids.length;i++)
                    ids[i] = Food.food[i].getImageID();


                CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(foodd, ids);
                recycler.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(rest_recycleview.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }




}