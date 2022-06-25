package com.example.myapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.StrictMode;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public  class MainMapActivity extends FragmentActivity implements OnMapReadyCallback,
        View.OnClickListener, GoogleMap.OnMapClickListener {

    private Button button;
    SupportMapFragment mapFragment;
    String parent_username;

    Spinner mapTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);

        initView();
        initListener();
        setMapTypeSpinner();

        parent_username = getIntent().getExtras().getString("parent_username","");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Timer timer = new Timer();
        timer.schedule(new RepeaterClass(getApplicationContext()),0,5000);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    ArrayList<Integer> mapTypeOptions = new ArrayList<>();
    private void setMapTypeSpinner() {
        for (int i = 1; i <= 5; i++)
            mapTypeOptions.add(i);
        ArrayAdapter<Integer> mapTypeAdapter = new ArrayAdapter<>(this, R.layout.spinner_map_type, R.id.map_type_spinner_tv, mapTypeOptions);
        mapTypeSpinner.setAdapter(mapTypeAdapter);
    }

    private void initView() {
        mapTypeSpinner = findViewById(R.id.map_type);
    }

    private void initListener() {
        serMapTypeSpinnerListener();
    }

    private void serMapTypeSpinnerListener() {
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                googleMap.setMapType(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == mapTypeSpinner.getId()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                googleMap.setMapType(ThreadLocalRandom.current().nextInt(0, 3 + 1));
//            }
//        } else {
//            Log.e("TAG", "onClick: ");
//        }
    }

    GoogleMap googleMap;

    @Override
    public void onMapReady(GoogleMap gMap) {
        googleMap = gMap;
        Log.e("onMapReady", String.valueOf(googleMap.getMapType()));
        double lat = 19.065689;
        double lon = 72.883431;

        googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Marker in Sydney"));
        googleMap.setBuildingsEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setOnMapClickListener(this);

//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15.0f));
    }

    public void justTemp() {
//        Toast.makeText(context,"New Location Set",Toast.LENGTH_SHORT).show();
        Log.d("justTemp", "justTemp: justTemp");
//        this does not works
//        mapFragment.getMapAsync(MainMapActivity.this::onMapReady);
    }

    @Override
    public void onMapClick(@NonNull @NotNull LatLng latLng) {
        Toast.makeText(getApplicationContext(), latLng.latitude + "\n" + latLng.longitude, Toast.LENGTH_SHORT).show();
    }

    //
    class RepeaterClass extends TimerTask {
        Context context;

        public RepeaterClass(Context applicationContext) {
            context = applicationContext;
        }

        Handler handler = new Handler();
        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //get new location and update lat lon
//                    Context context = MainMapActivity.this;
//                    Activity activity = MainMapActivity.this;
//
//                    LocaitonSenderToUI locaitonSenderToUI = new LocaitonSenderToUI(context,activity, parent_username);
//                    locaitonSenderToUI.runAsyncTask();

//                    LocationFinderClass locationFinderClass = new LocationFinderClass(activity,context);
//                    locationFinderClass.execute(paretn_username);

//                    Toast.makeText(getApplicationContext(),"New Location Set",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}