package com.example.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DatosPersonales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);
    }

    public void  GuardarPersona(View view){
        Intent miIntent = new Intent(getBaseContext(),SuguerirParqueadero.class);
        startActivity(miIntent);
    }
}
