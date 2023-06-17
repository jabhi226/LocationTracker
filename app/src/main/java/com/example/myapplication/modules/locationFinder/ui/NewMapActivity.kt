package com.example.myapplication.modules.locationFinder.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.myapplication.R
import com.example.myapplication.modules.core.utils.PermissionHelper
import com.example.myapplication.modules.core.utils.ResourceUtils
import com.example.myapplication.modules.core.utils.SharedPref
import com.example.myapplication.modules.locationFinder.data.MainApi
import com.example.myapplication.modules.locationFinder.data.RepeatTaskTimer
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.expressions.dsl.generated.interpolate
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.animation.CameraAnimatorOptions.Companion.cameraAnimatorOptions
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import java.util.Timer


class NewMapActivity : AppCompatActivity() {

    companion object {
        private const val GEOJSON_SOURCE_ID = "line"

    }

    private var latitude = 0.0
    private var longitude = 0.0
    private var currentZoom = 14.0

    private lateinit var mapView: MapView
    private lateinit var zoomIn: ImageView
    private lateinit var zoomOut: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_map)

        mapView = findViewById(R.id.map_view)
        zoomIn = findViewById(R.id.plus)
        zoomOut = findViewById(R.id.minus)

        zoomIn.setOnClickListener {
            setCamera(1)
        }
        zoomOut.setOnClickListener {
            setCamera(-1)
        }

        getPermission()
    }

    private fun getPermission() {
        val remainingPermissions = PermissionHelper.hasPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
        if (remainingPermissions.isNotEmpty()) {
            Toast.makeText(this, "Some permissions are not granted", Toast.LENGTH_SHORT).show()
            PermissionHelper.requestPermissions(this, remainingPermissions, 101)
        }

    }

    override fun onStart() {
        super.onStart()
//        setCamera(10, 55.0)
//        addAnnotationToMap()
        initLocationComponent()
        startLocationTracking()
    }


    private fun startLocationTracking() {
        val repeatTaskTimer = RepeatTaskTimer(SharedPref.getString(this, SharedPref.IP_ADDRESS)) { locationResponse ->
            when (locationResponse) {
                is MainApi.LocationApiResponse.Error -> {
                }

                is MainApi.LocationApiResponse.Success -> {
                    runOnUiThread {
                        locationResponse.data?.let { driverLocation ->
                            driverLocation.longitude?.let { longitude = it.toDouble() }
                            driverLocation.latitude?.let { latitude = it.toDouble() }
                            setCamera(0)
                            addAnnotationToMap()
                        }
                    }
                }
            }
        }

        val timer1 = Timer()
        timer1.scheduleAtFixedRate(repeatTaskTimer, 0 * 60, 1000 * 10)
    }

    private fun setCamera(mZoom: Int, mPitch: Double = 0.0, mBearing: Double = -45.0) {
        currentZoom += mZoom
        mapView.getMapboxMap().loadStyleUri(
            Style.MAPBOX_STREETS
        ) {
            mapView.camera.apply {
                val center = createCenterAnimator(
                    cameraAnimatorOptions(Point.fromLngLat(longitude, latitude)) {
                    }
                )
                val zoom = createZoomAnimator(
                    cameraAnimatorOptions(currentZoom) {
                        startValue(currentZoom - mZoom)
                    }
                ) {
                    duration = 2000
                    interpolator = AccelerateDecelerateInterpolator()
                }
                val bearing = createBearingAnimator(cameraAnimatorOptions(mBearing)) {
                    duration = 2000
                    interpolator = AccelerateDecelerateInterpolator()
                }
                val pitch = createPitchAnimator(
                    cameraAnimatorOptions(0.0) {
                        startValue(mPitch)
                    }
                ) {
                    duration = 2000
                    interpolator = AccelerateDecelerateInterpolator()
                }
                playAnimatorsTogether(bearing, pitch, center, zoom)
            }
        }
    }

    private fun addAnnotationToMap() {
// Create an instance of the Annotation API and get the PointAnnotationManager.
        ResourceUtils.bitmapFromDrawableRes(
            this,
            R.drawable.ic_location
        )?.let {
            val annotationApi = mapView.annotations
            val pointAnnotationManager = annotationApi.createPointAnnotationManager()
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(longitude, latitude))
                .withIconImage(it)
            pointAnnotationManager.create(pointAnnotationOptions)
        }
    }

    private fun initLocationComponent() {
        val locationComponentPlugin = mapView.location
        locationComponentPlugin.updateSettings {
            this.enabled = true
            this.locationPuck = LocationPuck2D(
                bearingImage = AppCompatResources.getDrawable(
                    this@NewMapActivity,
                    R.drawable.ic_navigation,
                ),
                shadowImage = AppCompatResources.getDrawable(
                    this@NewMapActivity,
                    R.drawable.mapbox_user_icon_shadow,
                ),
                scaleExpression = interpolate {
                    linear()
                    zoom()
                    stop {
                        literal(0.0)
                        literal(0.6)
                    }
                    stop {
                        literal(20.0)
                        literal(1.0)
                    }
                }.toJson()
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            101 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setCamera(0)
                initLocationComponent()
            }
        }
    }

}