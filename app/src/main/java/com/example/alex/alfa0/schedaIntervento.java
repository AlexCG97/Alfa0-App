package com.example.alex.alfa0;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class schedaIntervento extends AppCompatActivity implements OnMapReadyCallback {
    TextView TVNome, TVID, TVNomeP, TVCognome, TVVia, TVNumero, TVCitta, TVCap, TVChiamata, TVOperatore, TVCodice, TVData;
    Button ButtonStatus, ButtonChiusura;
    String Nomee, Indirizzo, urlGetScheda, urlSetStatus, DataOraCorrente, Status;
    GoogleMap mapView;
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
        TVData = findViewById(R.id.TVData);
        ButtonStatus = findViewById(R.id.ButtonStatus);
        ButtonChiusura = findViewById(R.id.ButtonChiusura);
        Nomee = getUsername();
        urlGetScheda = "http://192.168.1.21/gestioneambulanze/API_getScheda.php";
        urlSetStatus = "http://192.168.1.21/gestioneambulanze/API_setStatus.php";
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        ButtonChiusura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(schedaIntervento.this, "Chiusura missione", Toast.LENGTH_SHORT).show();
                aggiornaChiamata();
                Intent i = new Intent(schedaIntervento.this, Home.class);
                startActivity(i);
            }
        });

        ButtonStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(schedaIntervento.this, ButtonStatus);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch(id) {
                            case R.id.partenza:
                                getDataOra();
                                Status = "partenza";
                                updateStatus();
                                return true;
                            case R.id.arrivoPosto:
                                getDataOra();
                                Status = "arrivoPosto";
                                updateStatus();
                                return true;
                            case R.id.rientro:
                                getDataOra();
                                Status = "rientro";
                                updateStatus();
                                return true;
                            case R.id.bianco:
                                getDataOra();
                                Status = "bianco";
                                updateStatus();
                                return true;
                            case R.id.verde:
                                getDataOra();
                                Status = "verde";
                                updateStatus();
                                return true;
                            case R.id.giallo:
                                getDataOra();
                                Status = "giallo";
                                updateStatus();
                                return true;
                            case R.id.rosso:
                                getDataOra();
                                Status = "rosso";
                                updateStatus();
                                return true;
                            case R.id.arrivoPS:
                                getDataOra();
                                Status = "arrivoPS";
                                updateStatus();
                                return true;
                            case R.id.Libero:
                                getDataOra();
                                Status = "libero";
                                updateStatus();
                                return true;
                            default:
                                return false;
                        }
                        //return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private String getUsername() {
        Intent i = getIntent();
        String j = i.getStringExtra("Username");
        TVNome.setText(j);
        return j;
    }

    private void getDataOra(){
        Date Data = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        DataOraCorrente = format.format(Data);
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
                try {
                    loadIntoTextView(s);
                    LatLng myAddressCoordinates = getLocationFromAddress(Indirizzo);
                    mapView.addMarker(new MarkerOptions().position(myAddressCoordinates).title(Indirizzo));
                    mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(myAddressCoordinates, 20));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("Username", Nomee);
                    String query = builder.build().getEncodedQuery();
                    OutputStream os = con.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    con.connect();

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
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            TVID.setText(obj.getString("ID_chiamata"));
            TVNomeP.setText(obj.getString("Nome"));
            TVCognome.setText(obj.getString("Cognome"));
            TVVia.setText(obj.getString("Via"));
            TVNumero.setText(obj.getString("Numero"));
            TVCitta.setText(obj.getString("Citta"));
            TVCap.setText(obj.getString("CAP"));
            TVChiamata.setText(obj.getString("Motivo_chiamata"));
            TVOperatore.setText(obj.getString("Operatore"));
            TVCodice.setText(obj.getString("codice"));
            TVData.setText(obj.getString("Data_di_nascita"));
            Indirizzo = "Via " + TVVia.getText().toString() + " " + TVNumero.getText().toString() + " " + TVCitta.getText().toString();
        }
    }

    public LatLng getLocationFromAddress(String strAddress) {
        /*
         * A class for handling geocoding and reverse geocoding.
         * Geocoding is the process of transforming a street address or other description of a location into a (latitude, longitude) coordinate.
         * Reverse geocoding is the process of transforming a (latitude, longitude) coordinate into a (partial) address.
         * The amount of detail in a reverse geocoded location description may vary,
         * for example one might contain the full street address of the closest building,
         * while another might contain only a city name and postal code.
         *
         * See more at: https://developer.android.com/reference/android/location/Geocoder.html
         */
        Geocoder coder = new Geocoder(this);
        //A list because of getFromLocationName will return a list of address depending on how many result I want
        List<Address> address;
        LatLng p1 = null;
        try {
            /*
             * List<Address> getFromLocationName (String locationName, int maxResults)             *
             * | locationName | String: |           a user-supplied description of a location                      |
             * | maxResults	  |   int:  |max number of results to return. Smaller numbers (1 to 5) are recommended |
             * See more at: https://developer.android.com/reference/android/location/Geocoder.html
             */
            address = coder.getFromLocationName(strAddress, 2);
            if (address == null) {
                return null;
            }            //I take just the first result even if I've got list of 5 different LAT;LNG results
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();
            p1 = new LatLng(location.getLatitude(), location.getLongitude());
            return p1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mapView = googleMap;
        getJSON(urlGetScheda);
    }

    public void updateStatus(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlSetStatus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("Success")){
                    Toast.makeText(schedaIntervento.this, "Successo", Toast.LENGTH_SHORT).show();
                } else if(response.trim().equals("Error")) {
                    Toast.makeText(getApplicationContext(), "Erroreee!", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Errore:" + error.toString(), Toast.LENGTH_LONG).show();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID", TVID.getText().toString().trim());
                params.put("Status", Status.trim());
                params.put("Data", DataOraCorrente.trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void aggiornaChiamata(){
        //TODO: Inviare query a db che aggiorna la chiamata.
    }
}



