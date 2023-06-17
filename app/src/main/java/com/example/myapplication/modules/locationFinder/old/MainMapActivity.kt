//package com.example.myapplication.modules.locationFinder.old
//
//import android.content.Context
//import android.os.Bundle
//import android.os.Handler
//import android.os.StrictMode
//import android.util.Log
//import android.view.View
//import android.widget.AdapterView
//import android.widget.AdapterView.OnItemSelectedListener
//import android.widget.ArrayAdapter
//import android.widget.Button
//import android.widget.Spinner
//import android.widget.Toast
//import androidx.fragment.app.FragmentActivity
//import com.example.myapplication.R
//import com.example.myapplication.modules.locationFinder.data.MainApi
//import com.example.myapplication.modules.locationFinder.data.RepeatTaskTimer
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.GoogleMap.OnMapClickListener
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//import java.util.Timer
//import java.util.TimerTask
//
//class MainMapActivity : FragmentActivity(), OnMapReadyCallback, View.OnClickListener,
//    OnMapClickListener {
//
//    private var googleMap: GoogleMap? = null
//
//    private val button: Button? = null
//    private var parent_username: String? = null
//    private var mapTypeSpinner: Spinner? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_map)
//        initView()
//        initListener()
//        setMapTypeSpinner()
//        parent_username = intent.extras?.getString("parent_username", "")
//        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
//        StrictMode.setThreadPolicy(policy)
//        val mapFragment: SupportMapFragment? = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
//        mapFragment?.getMapAsync(this)
//        startLocationTracking()
//    }
//
//    private fun startLocationTracking() {
//        val repeatTaskTimer = RepeatTaskTimer(this) {
//            when (it) {
//                is MainApi.LocationApiResponse.Error -> {
//                    Toast.makeText(this, it.data, Toast.LENGTH_SHORT).show()
//                }
//
//                is MainApi.LocationApiResponse.Success -> {
//                    println("---> ${it.data?.latitude} | ${it.data?.latitude}")
//                }
//            }
//        }
//
//        val timer1 = Timer()
//        timer1.scheduleAtFixedRate(repeatTaskTimer, 0 * 60, 1000 * 2)
//    }
//
//    private var mapTypeOptions = ArrayList<Int>()
//    private fun setMapTypeSpinner() {
//        for (i in 1..5) mapTypeOptions.add(i)
//        val mapTypeAdapter =
//            ArrayAdapter(this, R.layout.spinner_map_type, R.id.map_type_spinner_tv, mapTypeOptions)
//        mapTypeSpinner?.adapter = mapTypeAdapter
//    }
//
//    private fun initView() {
//        mapTypeSpinner = findViewById(R.id.map_type)
//    }
//
//    private fun initListener() {
//        mapTypeSpinner?.onItemSelectedListener = object : OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                googleMap?.mapType = position
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//    }
//
//    override fun onClick(v: View) {
////        if (v.getId() == mapTypeSpinner.getId()) {
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                googleMap.setMapType(ThreadLocalRandom.current().nextInt(0, 3 + 1));
////            }
////        } else {
////            Log.e("TAG", "onClick: ");
////        }
//    }
//
//    override fun onMapReady(gMap: GoogleMap) {
//        googleMap = gMap
//        Log.e("onMapReady", googleMap?.mapType.toString())
//        val lat = 19.065689
//        val lon = 72.883431
//        googleMap?.addMarker(MarkerOptions().position(LatLng(lat, lon)).title("Marker in Sydney"))
//        googleMap?.isBuildingsEnabled = true
//        googleMap?.isTrafficEnabled = true
//        googleMap?.setOnMapClickListener(this)
//
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 15.0f))
//    }
//
//    fun justTemp() {
////        Toast.makeText(context,"New Location Set",Toast.LENGTH_SHORT).show();
//        Log.d("justTemp", "justTemp: justTemp")
//        //        this does not works
////        mapFragment.getMapAsync(MainMapActivity.this::onMapReady);
//    }
//
//    override fun onMapClick(latLng: LatLng) {
//        Toast.makeText(
//            applicationContext, """
//     ${latLng.latitude}
//     ${latLng.longitude}
//     """.trimIndent(), Toast.LENGTH_SHORT
//        ).show()
//    }
//
//    //
//    internal inner class RepeaterClass(var context: Context) : TimerTask() {
//        var handler = Handler()
//        override fun run() {
//            handler.post {
//                //get new location and update lat lon
////                    Context context = MainMapActivity.this;
////                    Activity activity = MainMapActivity.this;
////
////                    LocaitonSenderToUI locaitonSenderToUI = new LocaitonSenderToUI(context,activity, parent_username);
////                    locaitonSenderToUI.runAsyncTask();
//
////                    LocationFinderClass locationFinderClass = new LocationFinderClass(activity,context);
////                    locationFinderClass.execute(paretn_username);
//
////                    Toast.makeText(getApplicationContext(),"New Location Set",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//}