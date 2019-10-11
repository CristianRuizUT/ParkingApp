package com.example.parkingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.parkingapp.model.Persona;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMarkerDragListener,OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker markerTadeo,markerLaTadeo,markerPrueba,markerDrag,InfoWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // mMap.setMyLocationEnabled(true);

        //Punto U tadeo
        LatLng Utadeo = new LatLng(4.606698, -74.067626);
        markerTadeo = googleMap.addMarker(new MarkerOptions()
                .position(Utadeo)
                .title("Universidad Jorge Tadeo Lozano")
                .snippet("Ver mas!!!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        //Punto parqueadro la tadeo
        LatLng ParqueaderoUtadeo = new LatLng(4.605704, -74.067262);
        markerLaTadeo = googleMap.addMarker(new MarkerOptions()
                .position(ParqueaderoUtadeo)
                .title("Parqueadero la Tadeo")
                .snippet("Ver mas!!!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        //Marker para dar clicl encima
        LatLng UtadeoBiblioteca = new LatLng(4.606503, -74.067066);
        markerPrueba = googleMap.addMarker(new MarkerOptions()
                .position(UtadeoBiblioteca)
                .title("Biblioteca utadeo")
                .snippet("Ver mas!!!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        //Marker para arrastrar
        LatLng PosicionMover = new LatLng(4.607607, -74.068844);
        markerDrag = googleMap.addMarker(new MarkerOptions()
                .position(PosicionMover)
                .title("Manten presionado para fijar la ubicacion!")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        //Marker para arrastrar
        LatLng CineColombiaInfo = new LatLng(4.609416, -74.069080);
        InfoWindow = googleMap.addMarker(new MarkerOptions()
                .position(CineColombiaInfo)
                .title("Paqueadero CineColombia")
                .snippet("Ver mas!!!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


        //Camara donde va iniciar el mapa
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Utadeo,16));

        //Escuchar los eventos de los marcadores
        googleMap.setOnMarkerClickListener(this);
        //Escucha los enventos de arrastre
        googleMap.setOnMarkerDragListener(this);
        //Mostrar dialogo
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        if (marker.equals(InfoWindow)){
//            String latitud,longitud;
//            latitud = Double.toString(marker.getPosition().latitude);
//            longitud = Double.toString(marker.getPosition().longitude);
//            Toast.makeText(this,"La latitud y longitud son:" + latitud + "," + longitud,Toast.LENGTH_SHORT).show();
//        }
//        if (marker.equals(markerPrueba)){
//            String latitud,longitud;
//            latitud = Double.toString(marker.getPosition().latitude);
//            longitud = Double.toString(marker.getPosition().longitude);
//            Toast.makeText(this,"La latitud y longitud son:" + latitud + "," + longitud,Toast.LENGTH_SHORT).show();
//        }
//        if (marker.equals(markerTadeo)){
//            String latitud,longitud;
//            latitud = Double.toString(marker.getPosition().latitude);
//            longitud = Double.toString(marker.getPosition().longitude);
//            Toast.makeText(this,"La latitud y longitud son:" + latitud + "," + longitud,Toast.LENGTH_SHORT).show();
//        }
//        if (marker.equals(markerLaTadeo)){
//            String latitud,longitud;
//            latitud = Double.toString(marker.getPosition().latitude);
//            longitud = Double.toString(marker.getPosition().longitude);
//            Toast.makeText(this,"La latitud y longitud son:" + latitud + "," + longitud,Toast.LENGTH_SHORT).show();
//        }

        String latitud,longitud;
        latitud = Double.toString(marker.getPosition().latitude);
        longitud = Double.toString(marker.getPosition().longitude);
        Toast.makeText(this,"La latitud y longitud son:" + latitud + "," + longitud,Toast.LENGTH_SHORT).show();
        return false;
    }

    //Cuando inicia a arrastrar
    @Override
    public void onMarkerDragStart(Marker marker) {
        if (marker.equals(markerDrag)){
            Toast.makeText(this,"Start",Toast.LENGTH_LONG).show();
        }
    }

    //Cuando lo esta arrastrando
    @Override
    public void onMarkerDrag(Marker marker) {
        if (marker.equals(markerDrag)){
            String newTitle = String.format(Locale.getDefault(),
                    getString(R.string.marker_detail_latlng),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude);

            setTitle(newTitle);
        }
    }

    //Cuando termina de arrastrar
    @Override
    public void onMarkerDragEnd(Marker marker) {
        if (marker.equals(markerDrag)){
            Toast.makeText(this,"Finish",Toast.LENGTH_LONG).show();
            setTitle(R.string.app_name);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(InfoWindow)){
            PopupMaker.newInstance(marker.getTitle(),
                    getString(R.string.InformacionMostrat))
                    .show(getSupportFragmentManager(),null);
        }
        if (marker.equals(markerPrueba)){
            PopupMaker.newInstance(marker.getTitle(),
                    getString(R.string.InformacionMostratBiblioteca))
                    .show(getSupportFragmentManager(),null);
        }
        if (marker.equals(markerTadeo)){
            PopupMaker.newInstance(marker.getTitle(),
                    getString(R.string.InformacionMostratUtadeo))
                    .show(getSupportFragmentManager(),null);
        }
        if (marker.equals(markerLaTadeo)){
            PopupMaker.newInstance(marker.getTitle(),
                    getString(R.string.InformacionMostratLaTedo))
                    .show(getSupportFragmentManager(),null);
        }
    }


    public void  GoToPerson(View view) {
        Intent miIntent = new Intent(getBaseContext(),DatosPersonales.class);
        startActivity(miIntent);
    }

    public void  GoToParking(View view) {
        Intent miIntent = new Intent(getBaseContext(),SuguerirParqueadero.class);
        startActivity(miIntent);
    }
}
