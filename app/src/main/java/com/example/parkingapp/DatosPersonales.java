package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parkingapp.model.Persona;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatosPersonales extends AppCompatActivity {

    private List<Persona> ListPersona = new ArrayList<Persona>();
    ArrayAdapter<Persona> ArrayAdapterPersona;

    EditText PrimerNombre, SegundoNombre, PrimerApellido, SegundoApellido;
    ListView listV_personas;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Persona personaSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales);

        PrimerNombre = findViewById(R.id.txtPrimerNombre);
        SegundoNombre = findViewById(R.id.txtSegundoNombre);
        PrimerApellido = findViewById(R.id.txtPrimerApellido);
        SegundoApellido = findViewById(R.id.txtSegundoApellido);

        listV_personas = findViewById(R.id.lv_datosPersonas);
        inicializadorFirebase();
        listarDatos();

        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSeleccionado = (Persona) parent.getItemAtPosition(position);
                PrimerNombre.setText(personaSeleccionado.getPrimerNombre());
                SegundoNombre.setText(personaSeleccionado.getSegundoNombre());
                PrimerApellido.setText(personaSeleccionado.getPrimerApellido());
                SegundoApellido.setText(personaSeleccionado.getSegundoApellido());
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListPersona.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Persona p = objSnapshot.getValue(Persona.class);
                    ListPersona.add(p);

                    ArrayAdapterPersona = new ArrayAdapter<Persona>(DatosPersonales.this, android.R.layout.simple_expandable_list_item_1, ListPersona);
                    listV_personas.setAdapter(ArrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializadorFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    public void  GuardarPersona(View view){

        String Pnombre = PrimerNombre.getText().toString();
        String Snombre = SegundoNombre.getText().toString();
        String Papellido = PrimerApellido.getText().toString();
        String Sapellido = SegundoApellido.getText().toString();

        if (Pnombre.equals("")){
            PrimerNombre.setError("Campo requerido");
            return;
        }
        if (Papellido.equals("")){
            PrimerApellido.setError("Campo requerido");
            return;
        }

        Persona p = new Persona();
        p.setUid(UUID.randomUUID().toString());
        p.setPrimerNombre(Pnombre);
        p.setSegundoNombre(Snombre);
        p.setPrimerApellido(Papellido);
        p.setSegundoApellido(Sapellido);
        databaseReference.child("Persona").child(p.getUid()).setValue(p);


        Toast.makeText(this,"Bienvenido "+ Pnombre + ".!",Toast.LENGTH_LONG).show();

        PrimerNombre.setText("");
        SegundoNombre.setText("");
        PrimerApellido.setText("");
        SegundoApellido.setText("");

        Intent miIntent = new Intent(getBaseContext(),SuguerirParqueadero.class);
        startActivity(miIntent);
    }
}
