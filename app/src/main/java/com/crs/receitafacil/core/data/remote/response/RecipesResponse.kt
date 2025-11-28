package com.crs.receitafacil.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("owner")
    val ownerName: String? = null,
    @SerializedName("totalIngredients")
    val totalIngredients: Int,
    @SerializedName("preparationTime")
    val preparationTime: Int
)