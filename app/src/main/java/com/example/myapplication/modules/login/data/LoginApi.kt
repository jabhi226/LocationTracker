package com.example.myapplication.modules.login.data

import com.example.myapplication.modules.login.models.LoginSuccessResponse
import com.example.myapplication.modules.login.models.Parent
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class LoginApi(val loginResponse: (LoginResponse) -> Unit) {

    fun login(ip: String, username: String, password: String) {
        Thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://${ip}:3001/parent/login?username=$username&password=$password")
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
                                LoginSuccessResponse::class.java
                            )
                            loginResponse(LoginResponse.Success(loginSuccessResponse.result))
                        } else {
                            loginResponse(LoginResponse.Error(res.optString("result")))
                        }
                    }

                    else -> {
                        loginResponse(LoginResponse.Error("Something went wrong."))
                    }
                }
            } catch (e: Exception){
                e.printStackTrace()
                loginResponse(LoginResponse.Error("Something went wrong."))
            }
        }.start()
    }

    sealed class LoginResponse {
        data class Success(val data: Parent?) : LoginResponse()
        data class Error(val data: String) : LoginResponse()
    }
}