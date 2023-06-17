package com.example.myapplication.modules.login.models

import com.google.gson.annotations.SerializedName


data class LoginSuccessResponse(

    @SerializedName("status") var status: String? = null,
    @SerializedName("result") var result: Parent? = Parent()

)

