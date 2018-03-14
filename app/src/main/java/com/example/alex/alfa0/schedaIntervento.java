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
    String Nome, Cognome, Via, Numero, Citta, CAP, Chiamata, Operatore, Codice;
    TextView TVNome, TVID, TVNomeP, TVCognome, TVVia, TVNumero, TVCitta, TVCap, TVChiamata, TVOperatore, TVCodice;
    EditText EDData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheda_intervento);
        TVNome = findViewById(R.id.TVNome);
        TVID =  findViewById(R.id.TVID);
        TVNomeP = findViewById(R.id.TVNomeP);
        TVCognome = findViewById(R.id.TVCognome);
        TVVia=findViewById(R.id.TVVia);
        TVNumero = findViewById(R.id.TVNumero);
        TVCitta = findViewById(R.id.TVCitta);
        TVCap = findViewById(R.id.TVCap);
        TVChiamata = findViewById(R.id.TVChiamata);
        TVOperatore = findViewById(R.id.TVOperatore);
        TVCodice = findViewById(R.id.TVCodice);
        EDData = findViewById(R.id.EDData);
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

        JSONArray jsonArray = new JSONArray(json);
        //String[] schede = new String[jsonArray.length()];
        //intervento[] schedeInterventi = new intervento[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Toast.makeText(this, obj.getString("Nome"), Toast.LENGTH_SHORT).show();
            TVNomeP.setText(obj.getString("Nome"));
            TVCognome.setText(obj.getString("Cognome"));
            TVVia.setText(obj.getString("Via"));
            TVNumero.setText(obj.getString("Numero"));
            TVCitta.setText(obj.getString("Citta"));
            TVCap.setText(obj.getString("CAP"));
            TVChiamata.setText(obj.getString("Motivo_chiamata"));
            TVOperatore.setText(obj.getString("Operatore"));
            TVCodice.setText(obj.getString("codice"));

        }
    }
}


