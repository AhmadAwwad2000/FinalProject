package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.MySingleton;

public class swimming extends AppCompatActivity {
    TextView edt;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swimming);
edt=findViewById(R.id.edt);
        lst = findViewById(R.id.lst);
    }

    public void getweather(View view) {
        String url = "https://www.metaweather.com/api/location/44418/" ;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String[] days;
                        try {
                            JSONArray array = response.getJSONArray("consolidated_weather");
                            days = new String[array.length()];
                            for(int i = 0; i<array.length(); i++){
                                JSONObject obj = array.getJSONObject(i);
                                String weatherDay = "";
                                weatherDay = "state: " + obj.getString("weather_state_name") +
                                        "\n, date: " + obj.getString("applicable_date") +
                                        "\n, min: " + obj.getString("min_temp") +
                                        ", max: " + obj.getString("max_temp");
                                days[i] = weatherDay;
                            }
                            ArrayAdapter<String> itemsAdapter =
                                    new ArrayAdapter<String>(swimming.this, android.R.layout.simple_list_item_1,
                                            days);
                            lst.setAdapter(itemsAdapter);
                            JSONObject obj = array.getJSONObject(0);
                            double  min=Double.valueOf(obj.getString("min_temp"));
                            double max=Double.valueOf(obj.getString("max_temp"));
                            double range=(max-min)/2;
                            if(range>=20)
                                edt.setText("the range temp is "+range+" so you can swimming");
                            else
                                edt.setText("the range temp is "+range+" so you can not swimming");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}