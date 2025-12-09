package com.crs.receitafacil.ui.presentation.features.recipes.add.data.mappers

import com.crs.receitafacil.core.data.remote.request.AddIngredientRequest
import com.crs.receitafacil.core.data.remote.request.AddUpdateRecipeRequest
import com.crs.receitafacil.core.domain.model.IngredientsModel
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel

fun IngredientsModel.toAddIngredientsRequest(): AddIngredientRequest{
    return AddIngredientRequest(
        name = name,
        quantity = quantity
    )
}

fun AddUpdateRecipeRequestModel.toAddUpdateRecipeRequest(): AddUpdateRecipeRequest {
    return AddUpdateRecipeRequest(
        name = name,
        category = category,
        preparationTime = preparationTime,
        preparationMode = preparationMode,
        ingredients = ingredients.map { it.toAddIngredientsRequest() }
    )
}
