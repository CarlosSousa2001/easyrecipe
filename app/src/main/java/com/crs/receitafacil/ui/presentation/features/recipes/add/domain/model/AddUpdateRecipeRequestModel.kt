package com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model

import com.crs.receitafacil.core.domain.model.IngredientsModel

data class AddUpdateRecipeRequestModel(
    val name: String,
    val category: Int,
    val preparationTime: String,
    val preparationMode: String,
    val ingredients: List<IngredientsModel> = emptyList()
)
