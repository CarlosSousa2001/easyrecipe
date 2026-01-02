package com.crs.receitafacil.ui.presentation.features.recipes.detail.data.mappers

import com.crs.receitafacil.core.data.remote.response.IngredientsResponse
import com.crs.receitafacil.core.data.remote.response.RecipesDetailsResponse
import com.crs.receitafacil.core.domain.model.IngredientsModel
import com.crs.receitafacil.core.domain.model.RecipeDetailModel

fun IngredientsResponse.toIngredientsModel(): IngredientsModel {
    return IngredientsModel(
        id = id,
        name = name,
        quantity = quantity
    )
}

fun RecipesDetailsResponse.toRecipeDetail(): RecipeDetailModel {
        return RecipeDetailModel(
            id = id,
            name = name,
            category = category,
            preparationTime = preparationTime,
            preparationMode = preparationMode,
            ingredients = ingredients.map { item -> item.toIngredientsModel() },
            createAt = createAt
        )
}