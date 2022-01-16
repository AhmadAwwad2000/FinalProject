package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
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

public class reservations extends AppCompatActivity {
    private EditText roomnumber;
    private TextView username;
    String roomno="";
    private EditText day ,totalprice;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        username=findViewById(R.id.username);
        roomnumber=findViewById(R.id.roomnumber);
        Intent intent=getIntent();
        username.setText(intent.getStringExtra("username"));
        day=findViewById(R.id.day);
        totalprice=findViewById(R.id.totalprice);
        queue = Volley.newRequestQueue(this);

    }

    public void btnreservation(View view) {
        String uname=username.getText().toString();
        String res=roomnumber.getText().toString();

        checkroomres(uname,res);
    }
    public void check_if_room_are_exist(String room ,String uname){
        String url = "http://10.0.2.2/android_project/roomnumberres.php";
        RequestQueue queue = Volley.newRequestQueue(reservations.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    if(jsonObject.getString("message").equals("the reservation done !!")) {
                        roomno=room;
                        reservation(uname, room);
                    }
                    else
                        Toast.makeText(reservations.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(reservations.this,
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


                params.put("roomNumber", room);




                return params;
            }
        };


        queue.add(request);

    }
    /////////////////////////////////////////////////////////////////////////
    public void changestatus(String res){
        String url = "http://10.0.2.2/android_project/changestatusroom.php";
        RequestQueue queue = Volley.newRequestQueue(reservations.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(reservations.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    //changestatus(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(reservations.this,
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



                params.put("room_number", res);
                params.put("status","reserved");




                return params;
            }
        };

        queue.add(request);


    }
    //////////////////////////////////////////////////////////////////////////
    public void checkroomres(String uname ,String res){
        String url = "http://10.0.2.2/android_project/checkroomres.php";
        RequestQueue queue = Volley.newRequestQueue(reservations.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    if(jsonObject.getString("message").equals("true"))
                        check_if_room_are_exist(res,uname);
                    else
                        Toast.makeText(reservations.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(reservations.this,
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

                params.put("res", res);




                return params;
            }
        };

        queue.add(request);




    }
    public void reservation(String uname,String res){
        String url = "http://10.0.2.2/android_project/reservations.php";
        RequestQueue queue = Volley.newRequestQueue(reservations.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Toast.makeText(reservations.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    changestatus(res);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(reservations.this,
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

                params.put("username", uname);
                params.put("roomNumber", res);
                params.put("days", day.getText().toString());





                return params;
            }
        };

        queue.add(request);



    }


    public void btnprevious(View view) {
        Intent intent = new Intent(this ,room_recycleview.class);
        startActivity(intent);
    }

    public void showprice(View view) {

        int dayNo=Integer.parseInt(day.getText().toString());
        String roomNo=roomnumber.getText().toString();
        String url = "http://10.0.2.2/android_project/search_room.php?roomNumber=" + roomNo;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String s="";
                    try {
                        JSONObject obj = response.getJSONObject(0);
                        s =obj.getString("price");

                    }catch(JSONException exception){
                        Log.d("Error", exception.toString());
                    }
                int priceOneDay=Integer.parseInt(s);
                    computeprice(dayNo,priceOneDay);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(reservations.this, error.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);


    }
    public void computeprice(int Nday,int priceDay){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int total=Nday*priceDay;
                String tot=String.valueOf(total);
                totalprice.setText(tot);
            }
        });



    }
}