package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class confEat extends AppCompatActivity {
private EditText edteat;
private TextView show_price,okmessage;
 private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_eat);
        edteat=findViewById(R.id.edteat);
        show_price=findViewById(R.id.show_price);
        okmessage=findViewById(R.id.okmessage);
        queue = Volley.newRequestQueue(this);
    }

    public void getpriceandshow(View view) {

        String cat = edteat.getText().toString();
        String url = "http://10.0.2.2/android_project/cardview_fetch.php?foodname="+cat;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String s="";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                       s=obj.getString("foodprice");

                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                show_price.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(confEat.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }


            public void okmess(View view) {

        okmessage.setText("Your order is in progress, the meal will reach you as soon as possible");

            }

    public void go_to_eat_page(View view) {
        Intent intent = new Intent(this ,UserActivity.class);
        startActivity(intent);
    }
}