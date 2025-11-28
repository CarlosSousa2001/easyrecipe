package com.crs.receitafacil.ui.presentation.features.recipes.list.data.source

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.domain.exceptions.ErrorResponseException
import com.crs.receitafacil.core.domain.model.RecipesResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.recipes.list.data.mappers.toRecipesResponseModel
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class GetRecipesByUserRemoteDataSourceImpl @Inject constructor(
    private val recipeServiceApi: RecipeServiceApi
) : GetRecipesByUserRemoteDataSource {
    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponseModel>> {
        return try {
            val recipesResponse = recipeServiceApi.getRecipesByUser(category = category)
            val recipesResponseModel = recipesResponse.map {
                it.toRecipesResponseModel()
            }
            ServiceResult.Success(recipesResponseModel)
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}