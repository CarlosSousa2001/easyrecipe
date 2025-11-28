package com.crs.receitafacil.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("quantity")
    val quantity: String,
)
