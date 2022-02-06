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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class updateroom extends AppCompatActivity {
    EditText room_number,price,category,capacity,edtsearch,status;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateroom);
        room_number=findViewById(R.id.room_number);
        price=findViewById(R.id.price);
        category=findViewById(R.id.category);
        capacity=findViewById(R.id.capacity);
        status=findViewById(R.id.status);
        queue = Volley.newRequestQueue(this);

    }

    public void btnupdate_room(View view) {
        String rnum=room_number.getText().toString();
        String pr=price.getText().toString();
        String cat=category.getText().toString();
        String capa=capacity.getText().toString();
        String stat=status.getText().toString();
        updateroom(rnum,pr,cat,capa,stat);
    }
    public void updateroom(String rnum,String pr,String cat,String capa,String stat){
        String url = "http://10.0.2.2/android_project/update.php";
        RequestQueue queue = Volley.newRequestQueue(updateroom.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(updateroom.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(updateroom.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {

                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {


                Map<String, String> params = new HashMap<String, String>();


                params.put("room_number", rnum);
                params.put("price", pr);
                params.put("category", cat);
                params.put("capacity", capa);
                params.put("status", stat);



                return params;
            }
        };

        queue.add(request);



    }

    public void showall(View view) {
        String roomnumber = room_number.getText().toString();
        String url = "http://10.0.2.2/android_project/search_room.php?roomNumber=" + roomnumber;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> rooms = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                      //  String sent="Room Number : "+obj.getString("roomNumber")+"... Price :  "+obj.getString("price")+" .........Category :   "+obj.getString("category")+" ...capacity :  "+
                        //        obj.getString("capacity")+" ...status : "+obj.getString("status");
                        price.setText(obj.getString("price"));
                        category.setText(obj.getString("category"));
                        capacity.setText(obj.getString("capacity"));
                        status.setText(obj.getString("status"));

                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(updateroom.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);


    }
}