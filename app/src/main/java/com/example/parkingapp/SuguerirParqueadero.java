package com.example.parkingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parkingapp.model.Parqueadero;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuguerirParqueadero extends AppCompatActivity {
    private List<Parqueadero> ListParqueadero = new ArrayList<Parqueadero>();
    ArrayAdapter<Parqueadero> ArrayAdapterParqueadero;

    EditText Nombre, Direccion, Latitud, Longitud, Horarios, Telefono, Precios, Comentarios;
    ListView listV_Parqueaderos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Parqueadero ParqueaderoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suguerir_parqueadero);

        Nombre = findViewById(R.id.txtNombre);
        Direccion = findViewById(R.id.txtDireccion);
        Latitud = findViewById(R.id.txtLatitud);
        Longitud = findViewById(R.id.txtLongitud);
        Horarios = findViewById(R.id.txtHorarios);
        Telefono = findViewById(R.id.txtTelefono);
        Precios = findViewById(R.id.txtPrecios);
        Comentarios = findViewById(R.id.txtComentarios);

        listV_Parqueaderos = findViewById(R.id.lv_datosParqueaderos);
        inicializadorFirebase();
        listarDatos();

        listV_Parqueaderos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParqueaderoSeleccionado = (Parqueadero) parent.getItemAtPosition(position);
                Nombre.setText(ParqueaderoSeleccionado.getNombre());
                Direccion.setText(ParqueaderoSeleccionado.getDireccion());
                Latitud.setText(ParqueaderoSeleccionado.getLatitud());
                Longitud.setText(ParqueaderoSeleccionado.getLongitud());
                Horarios.setText(ParqueaderoSeleccionado.getHorarios());
                Telefono.setText(ParqueaderoSeleccionado.getTelefono());
                Precios.setText(ParqueaderoSeleccionado.getPrecios());
                Comentarios.setText(ParqueaderoSeleccionado.getComentarios());
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Parqueadero").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ListParqueadero.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Parqueadero p = objSnapshot.getValue(Parqueadero.class);
                    ListParqueadero.add(p);

                    ArrayAdapterParqueadero = new ArrayAdapter<Parqueadero>(SuguerirParqueadero.this, android.R.layout.simple_expandable_list_item_1, ListParqueadero);
                    listV_Parqueaderos.setAdapter(ArrayAdapterParqueadero);
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

    public void  GuardarSugPar(View view){

        String ValNombre = Nombre.getText().toString();
        String ValDireccion = Direccion.getText().toString();
        String ValLatitud = Latitud.getText().toString();
        String ValLongitud = Longitud.getText().toString();
        String ValHorarios = Horarios.getText().toString();
        String ValTelefono = Telefono.getText().toString();
        String ValPrecios = Precios.getText().toString();
        String ValComentarios = Comentarios.getText().toString();

        if (ValNombre.equals("")){
            Nombre.setError("Campo requerido");
            return;
        }
        if (ValDireccion.equals("")){
            Direccion.setError("Campo requerido");
            return;
        }
        if (ValLatitud.equals("")){
            Latitud.setError("Campo requerido");
            return;
        }
        if (ValLongitud.equals("")){
            Longitud.setError("Campo requerido");
            return;
        }
        if (ValHorarios.equals("")){
            Nombre.setError("Campo requerido");
            return;
        }
        if (ValPrecios.equals("")){
            Latitud.setError("Campo requerido");
            return;
        }

        Parqueadero p = new Parqueadero();
        p.setUid(UUID.randomUUID().toString());
        p.setNombre(ValNombre);
        p.setDireccion(ValDireccion);
        p.setLatitud(ValLatitud);
        p.setLongitud(ValLongitud);
        p.setHorarios(ValHorarios);
        p.setTelefono(ValTelefono);
        p.setPrecios(ValPrecios);
        p.setComentarios(ValComentarios);
        databaseReference.child("Parqueadero").child(p.getUid()).setValue(p);


        Toast.makeText(this,"Datos de parqueadero agregados",Toast.LENGTH_LONG).show();

        Nombre.setText("");
        Direccion.setText("");
        Latitud.setText("");
        Longitud.setText("");
        Horarios.setText("");
        Telefono.setText("");
        Precios.setText("");
        Comentarios.setText("");

    }

    public void  VerMapa(View view) {
        Intent miIntent = new Intent(getBaseContext(),MapsActivity.class);
        startActivity(miIntent);
    }
}
