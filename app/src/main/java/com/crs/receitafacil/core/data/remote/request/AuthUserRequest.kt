package com.crs.receitafacil.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class AuthUserRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
