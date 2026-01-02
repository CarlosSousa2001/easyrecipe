package com.crs.receitafacil.ui.presentation.features.recipes.detail.data.source

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.domain.exceptions.ErrorResponseException
import com.crs.receitafacil.core.domain.model.RecipeDetailModel
import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimplesResponseModel
import com.crs.receitafacil.ui.presentation.features.recipes.detail.data.mappers.toRecipeDetail
import com.crs.receitafacil.ui.presentation.features.recipes.detail.domain.source.RecipeDetailsRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class RecipeDetailsRemoteDataSourceImpl @Inject constructor(
    private val serviceApi: RecipeServiceApi
) : RecipeDetailsRemoteDataSource {
    override suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailModel> {
        return try {
            val response = serviceApi.getRecipeById(recipeId = recipeId)

            ServiceResult.Success(response.toRecipeDetail())
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }

    override suspend fun deleteRecipe(recipeId: String): ServiceResult<SimpleResponseModel> {
        return try {
            val response = serviceApi.deleteRecipe(recipeId = recipeId)

            if (response.isSuccessful) {
                ServiceResult.Success(response.toSimplesResponseModel())
            } else {
                ServiceResult.Error(message = response.message)
            }
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}