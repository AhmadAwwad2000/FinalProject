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

public class viewuser extends AppCompatActivity {

    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewuser);
        queue = Volley.newRequestQueue(this);
    }

    public void btnSearch_Click(View view) {
        EditText username = findViewById(R.id.username);
        ListView lst = findViewById(R.id.lstuser);
        String Username = username.getText().toString();
        String url = "http://10.0.2.2/android_project/viewuser.php?username=" + Username;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> user = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String s=obj.getString("username")+" "+obj.getString("phoneNumber")+" "+obj.getString("email");
                        user.add(s);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                String[] arr = new String[user.size()];
                arr = user.toArray(arr);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        viewuser.this, android.R.layout.simple_list_item_1,
                        arr);
                lst.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewuser.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);

    }


    public void viewall(View view) {
        ListView lstalluser=findViewById(R.id.lstalluser);
        String url = "http://10.0.2.2/android_project/getalluser.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> user = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String s=obj.getString("username")+" "+obj.getString("phoneNumber")+" "+obj.getString("email");
                        user.add(s);
                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }
                String[] arr = new String[user.size()];
                arr = user.toArray(arr);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        viewuser.this, android.R.layout.simple_list_item_1,
                        arr);
                lstalluser.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(viewuser.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);



    }
}