package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

public class login extends AppCompatActivity {
    private EditText username,password;
    private CheckBox check;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static final  String NAME = "NAME"  ;
    private static final String PASS="PASS";
    private  static final String FLAG ="FLAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        check=findViewById(R.id.check);
        prefs= PreferenceManager.getDefaultSharedPreferences(this );
        editor=prefs.edit();
        boolean flag=prefs.getBoolean(FLAG,false);
        if(flag){
            String name=prefs.getString(NAME,"");
            String pass=prefs.getString(PASS,"");
            username.setText(name);
            password.setText(pass);
            check.setChecked(true);
        }


    }

    public void btnlogin(View view) {
        String uname=username.getText().toString();
        String pass=password.getText().toString();
        if(check.isChecked()){
            editor.putString(NAME,uname);
            editor.putString(PASS,pass);
            editor.putBoolean(FLAG,true);
            editor.commit();

        }
        loginn(uname,pass);


    }
    public void createnewactivity(String uname){
        Intent intent = new Intent(this ,mainpage.class);
        intent.putExtra("username",uname);
        startActivity(intent);
    }
    public void useractivity(String uname){
        Intent intent = new Intent(this ,UserActivity.class);
        intent.putExtra("username",uname);
        startActivity(intent);
    }
    public void loginn(String uname,String pass){
        String url = "http://10.0.2.2/android_project/check.php";
        RequestQueue queue = Volley.newRequestQueue(login.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(login.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    if(jsonObject.getString("message").equals("true")){
                        if(uname.endsWith("@admin"))
                        createnewactivity(uname);
                        else if(uname.endsWith("@user"))
                            useractivity(uname);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(login.this,
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
                params.put("password", pass);



                return params;
            }
        };

        queue.add(request);



    }


}

