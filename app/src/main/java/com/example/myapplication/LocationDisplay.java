package com.example.myapplication;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.zip.Inflater;

public class LocationDisplay extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    SupportMapFragment mapFragment;
    double lat = 19.065689;
    double lon = 72.883431;
    LayoutInflater inflater;

    public void setUpMapIfNeeded(Activity activity, Context context) {
        setContentView(R.layout.activity_main_map);
        // Do a null check to confirm that we have not already instantiated the map.

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(LocationDisplay.this::onMapReady);
        mapFragment.getMapAsync(this);

//            // Check if we were successful in obtaining the map.
//            if (gmap != null) {
//                googleMap = new GoogleMapsManager(mapFragment, getBaseContext(), this);
//            }
        }

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng sydney;

        sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15.0f));
    }

}
