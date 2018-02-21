package com.example.alex.alfa0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
            public void onClick(View v){
                controlloSchede();
            }
        });


    }

    private void getUsername(){
        Intent i= getIntent();
        String j = i.getStringExtra("Username");
        TVNome.setText(j);
    }

    private void controlloSchede(){




    }

}
