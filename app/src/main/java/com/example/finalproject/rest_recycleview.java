package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import model.CaptionedImagesAdapter;
import model.Pizza;
import model.getfood;

public class rest_recycleview extends AppCompatActivity {

    private RequestQueue queue;
    ArrayList<getfood> food;
   String [] fooddb;
   TextView tt;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_recycleview);
tt=findViewById(R.id.tt);
        recycler= findViewById(R.id.pizza_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        queue = Volley.newRequestQueue(this);
      fooddb  =new String[5];
        getdatafromdatabase();







       // String[] captions = new String[Pizza.pizzas.length];
        int[] ids = new int[Pizza.pizzas.length];

        for(int i = 0; i<ids.length;i++){
         //   captions[i] = Pizza.pizzas[i].getName();
            ids[i] = Pizza.pizzas[i].getImageID();
        }
      //  recycler.setLayoutManager(new LinearLayoutManager(this));
       // CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(fooddb, ids);
        //recycler.setAdapter(adapter);
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
                ArrayList<String> foods = new ArrayList<>();
               String [] foodd=new String[response.length()];

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String s=obj.getString("foodname");
                        foods.add(s);
                        foodd[i]=obj.getString("foodname");
                      //  food.add(new getfood(obj.getString("foodname")));

                        Log.d("-------------------", foodd[i]);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                int[] ids = new int[Pizza.pizzas.length];

                for(int i = 0; i<ids.length;i++){
                    //   captions[i] = Pizza.pizzas[i].getName();
                    ids[i] = Pizza.pizzas[i].getImageID();
                }

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
    public void getarr(String [] arr){
for(int i =0;i<arr.length;i++){
    System.out.println(arr[i]);
    fooddb[i]=arr[i];
}
    }



}