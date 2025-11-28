package com.crs.receitafacil.ui.presentation.features.recipes.list.data.repository

import com.crs.receitafacil.core.domain.model.RecipesResponseModel
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecipesByUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: GetRecipesByUserRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
): GetRecipesByUserRepository {
    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponseModel>> {
        return withContext(dispatchersProvider.io()){
            remoteDataSource.getRecipesByUser(category)
        }
    }
}