package com.example.myapplication.modules.locationFinder.data

import com.example.myapplication.modules.locationFinder.model.DriverLocation
import com.example.myapplication.modules.locationFinder.model.LocationResponse
import com.example.myapplication.modules.login.data.LoginApi
import com.example.myapplication.modules.login.models.LoginSuccessResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainApi(val loginResponse: (LocationApiResponse) -> Unit) {

    fun getChildLocation(ip: String) {
        Thread {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://$ip:3001/driverLocation/by/busnumber?busNumber=101")
                .addHeader("Content-Type", "application/json")
                .build()
            val response = client.newCall(request).execute()

            when (response.code) {
                200 -> {
                    val res = JSONObject(response.body?.string().toString())
                    println("----> $res")
                    if (res.optString("status").uppercase() == "OK") {
                        val loginSuccessResponse = Gson().fromJson(
                            res.toString(),
                            LocationResponse::class.java
                        )
                        loginResponse(LocationApiResponse.Success(loginSuccessResponse.result))
                    } else {
                        loginResponse(LocationApiResponse.Error(res.optString("result")))
                    }
                }

                else -> {
                    loginResponse(LocationApiResponse.Error("Something went wrong."))
                }
            }

        }.start()
    }


    sealed class LocationApiResponse() {
        data class Success(val data: DriverLocation?) : LocationApiResponse()
        data class Error(val data: String) : LocationApiResponse()
    }

}