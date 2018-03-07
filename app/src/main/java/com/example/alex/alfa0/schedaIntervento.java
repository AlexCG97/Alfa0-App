package com.example.alex.alfa0;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
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
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        String url = "http://10.0.0.238/gestioneambulanze/API_getScheda.php";
        getJSON(url);



    }

    private String getUsername() {
        Intent i = getIntent();
        String j = i.getStringExtra("Username");
        TVNome.setText(j);
        return j;
    }
/*
    private void getScheda(String url){

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {



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
*/

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;

                    while ((json = bufferedReader.readLine()) != null) {

                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }

        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }




    private void loadIntoListView(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);
        //creating a string array for listview
        String[] schede = new String[jsonArray.length()];
        intervento[] schedeInterventi = new intervento[jsonArray.length()];
        //looping through all the elements in json array
        for (int i = 0; i < jsonArray.length(); i++) {
            //getting json object from the json array
            JSONObject obj = jsonArray.getJSONObject(i);
            //getting the name from the json object and putting it inside string array
            //schede[i] = obj.getString("name");

            /*schedeInterventi[i].setId(obj.getString("ID_chiamata"));
            schedeInterventi[i].setNome(obj.getString("Nome"));
            schedeInterventi[i].setCognome(obj.getString("Cognome"));
            schedeInterventi[i].setData(obj.getString("Data_di_nascita"));
            schedeInterventi[i].setVia(obj.getString("Via"));
            schedeInterventi[i].setNumero(obj.getString("Numero"));
            schedeInterventi[i].setCap(obj.getString("CAP"));
            schedeInterventi[i].setChiamata(obj.getString("Motivo_chiamata"));
            schedeInterventi[i].setOperatore(obj.getString("Operatore"));
            schedeInterventi[i].setCodice(obj.getString("codice"));
            schedeInterventi[i].setAttivo(obj.getString("Attivo"));
            Toast.makeText(this, schedeInterventi[i].toString(), Toast.LENGTH_SHORT).show();*/


        }
        //the array adapter to load data into list
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schede);

        //attaching adapter to listview
        //listView.setAdapter(arrayAdapter);
    }

}


