package com.crs.receitafacil.ui.presentation.features.recipes.add.domain.repository

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel

interface AddUpdateRecipeRepository {
    suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel>
    suspend fun updateRecipe(recipeId: String, addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel>
}