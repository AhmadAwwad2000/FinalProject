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

public class signup extends AppCompatActivity {
private EditText firstname,lastname,password,email,username,phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        phonenumber=findViewById(R.id.phonenumber);
    }

    public void btnReg(View view) {
        String fname=firstname.getText().toString();
        String lname=lastname.getText().toString();
        String pass=password.getText().toString();
        String mail=email.getText().toString();
        String uname=username.getText().toString();
        String phone=phonenumber.getText().toString();
        signup(fname,lname,pass,mail,uname,phone);


    }
    public void signup(String fname,String lname,String pass,String mail,String uname, String phone){
        String url = "http://10.0.2.2/android_project/adduser.php";
        RequestQueue queue = Volley.newRequestQueue(signup.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(signup.this,
                            jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(signup.this,
                        "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("username", uname);
                params.put("password", pass);
                params.put("phoneNumber", phone);
                params.put("first_name", fname);
                params.put("last_name", lname);
                params.put("email", mail);


                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);



    }
}