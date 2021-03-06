package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddRoom extends AppCompatActivity {
    EditText room_number,price,category,capacity,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        room_number=findViewById(R.id.room_number);
        price=findViewById(R.id.price);
        category=findViewById(R.id.category);
        capacity=findViewById(R.id.capacity);
        status=findViewById(R.id.status);
    }

    public void btnadd_room(View view) {
        String rnum=room_number.getText().toString();
        String pr=price.getText().toString();
        String cat=category.getText().toString();
        String capa=capacity.getText().toString();
        String stat=status.getText().toString();
        checkroom(rnum,pr,cat,capa,stat);

    }
    public void checkroom(String rnum ,String pr ,String cat, String capa ,String stat){
        String url = "http://10.0.2.2/android_project/roomnumber.php";
        RequestQueue queue = Volley.newRequestQueue(AddRoom.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.getString("message").equals("true"))
                        addroom(rnum,pr,cat,capa,stat);
                    else
                        Toast.makeText(AddRoom.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AddRoom.this,
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


                params.put("roomNumber", rnum);




                return params;
            }
        };

        queue.add(request);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addroom(String rnum,String pr,String cat,String capa,String stat){
        String url = "http://10.0.2.2/android_project/addroom.php";
        RequestQueue queue = Volley.newRequestQueue(AddRoom.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(AddRoom.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(AddRoom.this,
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
                params.put("status",stat);



                return params;
            }
        };

        queue.add(request);



    }


}