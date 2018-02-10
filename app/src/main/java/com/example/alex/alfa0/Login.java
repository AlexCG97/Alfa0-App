package com.example.alex.alfa0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Login extends AppCompatActivity {
    Button btLogin;
    EditText Username, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = (Button) findViewById(R.id.btLogin);
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);

     btLogin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v){
             Login();
         }
     });
    }

    private void Login(){
        String url = "http://192.168.1.16/gestioneambulanze/login_mobile.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(getApplicationContext(), "Login effettuato correttamente!", Toast.LENGTH_LONG).show();
                    startActivity((new Intent(Login.this, Home.class)));
                } else {
                    Toast.makeText(getApplicationContext(), "Errore!", Toast.LENGTH_LONG).show();

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
                params.put("UserName", Username.getText().toString().trim());
                params.put("Password", Password.getText().toString().trim());

                return params;
            }


        };
        requestQueue.add(stringRequest);




    }

}
