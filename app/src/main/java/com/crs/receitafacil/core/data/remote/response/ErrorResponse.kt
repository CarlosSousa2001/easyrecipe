package com.crs.receitafacil.core.data.remote.response

import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    @SerializedName("httpCode")
    var httpCode: Int,
    @SerializedName("message")
    var message: String,
)
