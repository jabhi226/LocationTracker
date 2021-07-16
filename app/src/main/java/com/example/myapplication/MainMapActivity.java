package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.os.StrictMode;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;

public class MainMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button button;
    private boolean counter = true;
    SupportMapFragment mapFragment;
    double lat = 19.065689;
    double lon = 72.883431;
    String parent_username;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        context = (Context) MainMapActivity.this;

        parent_username = getIntent().getExtras().getString("parent_username","");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Timer timer = new Timer();
        timer.schedule(new RepeaterClass(getApplicationContext()),0,5000);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        LatLng sydney;

        sydney = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15.0f));
    }

    public void justTemp() {
//        Toast.makeText(context,"New Location Set",Toast.LENGTH_SHORT).show();
        Log.d("justTemp", "justTemp: justTemp");
//        this does not works
//        mapFragment.getMapAsync(MainMapActivity.this::onMapReady);
    }

    //
    class RepeaterClass extends TimerTask {
        Context context1 = MainMapActivity.this;

        public RepeaterClass(Context applicationContext) {
            context1 = applicationContext;
        }

        Handler handler = new Handler();
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //get new location and update lat lon
                    Context context = MainMapActivity.this;
                    Activity activity = MainMapActivity.this;

                    LocaitonSenderToUI locaitonSenderToUI = new LocaitonSenderToUI(context,activity, parent_username);
                    locaitonSenderToUI.runAsyncTask();

//                    LocationFinderClass locationFinderClass = new LocationFinderClass(activity,context);
//                    locationFinderClass.execute(paretn_username);

//                    Toast.makeText(getApplicationContext(),"New Location Set",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}