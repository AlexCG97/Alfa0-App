package com.example.alex.alfa0;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
    Button BTAggiorna;
    TextView TVNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BTAggiorna = (Button) findViewById(R.id.BTAggiorna);
        TVNome = (TextView) findViewById(R.id.TVNome);
        getUsername();

        BTAggiorna.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controlloSchede();
            }
        });


    }

    private String getUsername() {
        Intent i = getIntent();
        String j = i.getStringExtra("Username");
        TVNome.setText(j);
        return j;
    }

    private void controlloSchede() {
        String url = "http://10.0.0.194/gestioneambulanze/API_controlloSchede.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.trim().equals("presente")) {
                        //Intent i = new Intent(Home.this, schedaIntervento.class);
                        String strName = getUsername();
                        //i.putExtra("Username", strName);
                        Toast.makeText(getApplicationContext(), strName, Toast.LENGTH_LONG).show();
                       // startActivity(i);
                        Toast.makeText(getApplication(), "presente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Errore!", Toast.LENGTH_LONG).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Errore:" + error.toString(), Toast.LENGTH_LONG).show();

                }

            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Username", TVNome.getText().toString().trim());
                    return params;
                }


            };
        requestQueue.add(stringRequest);






/*
new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if(response.trim().equals("success")){

                }else{
                    //TODO: Se non cè rifà dopo 30 sec
                }
            }
        }, 30000);


*/
        }

    }


