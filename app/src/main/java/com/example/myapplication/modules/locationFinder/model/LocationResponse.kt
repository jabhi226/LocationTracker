package com.example.myapplication.modules.locationFinder.model

import com.google.gson.annotations.SerializedName


data class LocationResponse (

    @SerializedName("status" ) var status : String? = null,
    @SerializedName("result" ) var result : DriverLocation? = DriverLocation()

)