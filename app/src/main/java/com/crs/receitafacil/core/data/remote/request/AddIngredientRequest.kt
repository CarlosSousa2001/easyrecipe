package com.crs.receitafacil.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class AddIngredientRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String,
)
