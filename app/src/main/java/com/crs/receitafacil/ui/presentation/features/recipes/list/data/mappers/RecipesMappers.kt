package com.crs.receitafacil.ui.presentation.features.recipes.list.data.mappers

import com.crs.receitafacil.core.data.remote.response.RecipesResponse
import com.crs.receitafacil.core.domain.model.RecipesResponseModel

fun RecipesResponse.toRecipesResponseModel(): RecipesResponseModel{
    return RecipesResponseModel(
        id = id,
        name = name,
        ownerName = ownerName,
        totalIngredients = totalIngredients,
        preparationTime = preparationTime
    )
}
