package com.example.myapplication.modules.locationFinder.data

import android.content.Context
import com.example.myapplication.modules.core.utils.SharedPref
import com.example.myapplication.modules.locationFinder.data.MainApi
import java.util.TimerTask


class RepeatTaskTimer(private val ipAddress: String, val response: (MainApi.LocationApiResponse) -> Unit) :
    TimerTask() {

    override fun run() {
        MainApi {
            response(it)
        }.getChildLocation(ipAddress)
    }
}