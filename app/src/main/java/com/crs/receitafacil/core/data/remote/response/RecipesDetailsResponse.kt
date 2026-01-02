package com.crs.receitafacil.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipesDetailsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("owner")
    val ownerName: String? = null,
    @SerializedName("preparationTime")
    val preparationTime: String,
    @SerializedName("preparationMode")
    val preparationMode: String,
    @SerializedName("createAt")
    val createAt: String,
    @SerializedName("ingredients")
    val ingredients: List<IngredientsResponse>
)
