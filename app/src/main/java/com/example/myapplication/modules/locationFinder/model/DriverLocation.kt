package com.example.myapplication.modules.locationFinder.model

import com.google.gson.annotations.SerializedName

data class DriverLocation(

    @SerializedName("_id") var Id: String? = null,
    @SerializedName("busNumber") var busNumber: String? = null,
    @SerializedName("__v") var _v: Int? = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("timestamp") var timestamp: String? = null

)