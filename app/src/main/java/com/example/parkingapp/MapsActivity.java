package com.example.parkingapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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

        LatLng Utadeo = new LatLng(4.606698, -74.067626);
        mMap.addMarker(new MarkerOptions().position(Utadeo).title("Universidad Jorge Tadeo Lozano").snippet("Descripcion").icon(BitmapDescriptorFactory.fromResource(R.drawable.parkingv1)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Utadeo,17));

        LatLng ParqueaderoUtadeo = new LatLng(4.605704, -74.067262);
        mMap.addMarker(new MarkerOptions().position(ParqueaderoUtadeo).title("Parqueadero la Tadeo").snippet("Descripcion 2").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(ParqueaderoUtadeo));
    }
}
