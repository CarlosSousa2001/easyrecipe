package com.crs.receitafacil.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("isSuccessful") val isSuccessful: Boolean,
    @SerializedName("message") val message: String,
)
