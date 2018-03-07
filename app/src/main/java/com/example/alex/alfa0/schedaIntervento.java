package com.example.alex.alfa0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;



public class schedaIntervento extends AppCompatActivity {
    TextView TVNome, TVID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_intervento);
        TVNome = (TextView) findViewById(R.id.TVNome);
        getUsername();
        //JSONObject schedaIntervento = new JSONObject().getJSONObject();
        String url = "http://10.0.0.238/gestioneambulanze/API_getScheda.php?";
        getScheda(url);



    }

    private String getUsername() {
        Intent i = getIntent();
        String j = i.getStringExtra("Username");
        TVNome.setText(j);
        return j;
    }

    private void getScheda(String url){

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                        try {

                            String s = "";
                            JSONObject ob = new JSONObject(s);
                            JSONArray arr = ob.getJSONArray("records");

                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject o = arr.getJSONObject(i);
                                intervento a = new intervento();
                                a.setId(o.getString("Id_chiamata"));
                                Toast.makeText(schedaIntervento.this, a.getId(), Toast.LENGTH_SHORT).show();
                                TVID.setText(a.getId());
                                /*
                                System.out.println((o.getString("ID_Chiamata")));
                                System.out.println((o.getString("Nome")));
                                System.out.println((o.getString("Cognome")));
                                System.out.println((o.getString("Data_di_nascita")));
                                System.out.println((o.getString("Via")));
                                System.out.println((o.getString("Numero")));
                                System.out.println((o.getString("Citta")));
                                System.out.println((o.getString("Comune")));
                                System.out.println((o.getString("CAP")));
                                System.out.println((o.getString("Motivo_chiamata")));
                                System.out.println((o.getString("Operatore")));
                                System.out.println((o.getString("codice")));
                                */
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
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









}
