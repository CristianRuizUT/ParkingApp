package com.example.parkingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuguerirParqueadero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suguerir_parqueadero);
    }

    public void  GuardarSugPar(View view){
        Intent miIntent = new Intent(getBaseContext(),MainActivity.class);
        startActivity(miIntent);
    }
}
