package com.crs.receitafacil.ui.presentation.features.recipes.list.domain.repository

import com.crs.receitafacil.core.domain.model.RecipesResponseModel
import com.crs.receitafacil.core.utils.ServiceResult

interface GetRecipesByUserRepository {
    suspend fun getRecipesByUser(category: Int? = null): ServiceResult<List<RecipesResponseModel>>
}