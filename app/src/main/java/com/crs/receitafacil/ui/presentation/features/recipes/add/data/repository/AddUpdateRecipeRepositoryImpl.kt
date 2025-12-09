package com.crs.receitafacil.ui.presentation.features.recipes.add.data.repository

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.repository.AddUpdateRecipeRepository
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.source.AddUpdateRecipeRemoteDataSource
import javax.inject.Inject

class AddUpdateRecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: AddUpdateRecipeRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
) : AddUpdateRecipeRepository {
    override suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel> {
        return with(dispatchersProvider.io()) {
            remoteDataSource.addRecipe(addUpdateRecipeRequestModel)
        }
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimpleResponseModel> {
        return with(dispatchersProvider.io()) {
            remoteDataSource.updateRecipe(recipeId, addUpdateRecipeRequestModel)
        }
    }
}