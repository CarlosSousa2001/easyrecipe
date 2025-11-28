package com.crs.receitafacil.core.domain.model

data class RecipesResponseModel(
    val id: String,
    val name: String,
    val ownerName: String? = null,
    val totalIngredients: Int,
    val preparationTime: Int
)
