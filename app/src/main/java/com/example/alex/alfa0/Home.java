package com.example.alex.alfa0;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
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
    MediaPlayer allarme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        allarme = MediaPlayer.create(getApplicationContext(), R.raw.ring);

        BTAggiorna = findViewById(R.id.BTAggiorna);
        TVNome = findViewById(R.id.TVNome);
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
        String url = "http://www.alexghiurca.com/alfa0/API/API_controlloSchede.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.trim().equals("presente")) {
                        Intent i = new Intent(Home.this, schedaIntervento.class);
                        String strName = getUsername();
                        i.putExtra("Username", strName);
                        allarme.start();
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Errore, scheda non presente!", Toast.LENGTH_LONG).show();
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
        }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Uscita")
                .setMessage("Vuoi effettuare il Log Out?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Home.super.onBackPressed();
                    }
                }).create().show();
    }
}


