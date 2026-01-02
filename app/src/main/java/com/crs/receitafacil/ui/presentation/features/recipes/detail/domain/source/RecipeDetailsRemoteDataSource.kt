package com.crs.receitafacil.ui.presentation.features.recipes.detail.domain.source

import com.crs.receitafacil.core.domain.model.RecipeDetailModel
import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult

interface RecipeDetailsRemoteDataSource {
    suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailModel>
    suspend fun deleteRecipe(recipeId: String): ServiceResult<SimpleResponseModel>
}