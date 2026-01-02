package com.crs.receitafacil.ui.presentation.features.recipes.detail.data.repository

import com.crs.receitafacil.core.domain.model.RecipeDetailModel
import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.recipes.detail.domain.repository.RecipeDetailRepository
import com.crs.receitafacil.ui.presentation.features.recipes.detail.domain.source.RecipeDetailsRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipeDetailsRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
): RecipeDetailRepository {
    override suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailModel> {
        return withContext(dispatchersProvider.io()) {
            remoteDataSource.getRecipeById(recipeId)
        }
    }

    override suspend fun deleteRecipe(recipeId: String): ServiceResult<SimpleResponseModel> {
        return withContext(dispatchersProvider.io()) {
            remoteDataSource.deleteRecipe(recipeId)
        }
    }
}