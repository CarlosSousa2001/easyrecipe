package com.crs.receitafacil.core.domain.model

data class RecipeDetailModel(
    val id: String,
    val name: String,
    val category: String,
    val preparationTime: String,
    val preparationMode: String,
    val ingredients: List<IngredientsModel>,
    val createAt: String
)
