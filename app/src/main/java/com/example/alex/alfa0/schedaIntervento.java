package com.example.alex.alfa0;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class schedaIntervento extends AppCompatActivity {
    TextView TVNome, TVID, TVNomeP, TVCognome, TVVia, TVNumero, TVCitta, TVCap, TVChiamata, TVOperatore, TVCodice;
    EditText EDData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_intervento);
        TVNome = (TextView) findViewById(R.id.TVNome);
        getUsername();
        //JSONObject schedaIntervento = new JSONObject().getJSONObject();
        String url = "http://192.168.1.10/gestioneambulanze/API_getScheda.php";
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
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoTextView(s);
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




    private void loadIntoTextView(String json) throws JSONException {
        String Nome, Cognome, Via, Numero, Citta, CAP, Chiamata, Operatore, Codice;
        JSONArray jsonArray = new JSONArray(json);
        //String[] schede = new String[jsonArray.length()];
        //intervento[] schedeInterventi = new intervento[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            
            Nome = obj.getString(("Nome"));
            Cognome = obj.getString("Cognome");
            Via = obj.getString("Via");
            Numero = obj.getString("Numero");
            Citta = obj.getString("Citta");
            CAP = obj.getString("CAP");
            Chiamata = obj.getString("Motivo_chiamata");
            Operatore = obj.getString("Operatore");
            Codice = obj.getString("Codice");
            TVNomeP.setText(Nome);
            TVCognome.setText(Cognome);
            //manca data di nascita da fixare
            TVVia.setText(Via);
            TVNumero.setText(Numero);
            TVCitta.setText(Citta);
            TVCap.setText(CAP);
            TVChiamata.setText(Chiamata);
            TVOperatore.setText(Operatore);
            TVCodice.setText(Codice);
            /*schedeInterventi[i].setId(obj.getString("ID_chiamata"));
            schedeInterventi[i].setNome(obj.getString("Nome"));
            schedeInterventi[i].setCognome(obj.getString("Cognome"));
            schedeInterventi[i].setData(obj.getString("Data_di_nascita"));
            schedeInterventi[i].setVia(obj.getString("Via"));
            schedeInterventi[i].setNumero(obj.getString("Numero"));
            CITTà PURE
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


