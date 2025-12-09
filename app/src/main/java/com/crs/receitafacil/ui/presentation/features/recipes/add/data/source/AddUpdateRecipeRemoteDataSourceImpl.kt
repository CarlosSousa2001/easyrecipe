package com.crs.receitafacil.ui.presentation.features.recipes.add.data.source

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.domain.exceptions.ErrorResponseException
import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimplesResponseModel
import com.crs.receitafacil.ui.presentation.features.recipes.add.data.mappers.toAddUpdateRecipeRequest
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.source.AddUpdateRecipeRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class AddUpdateRecipeRemoteDataSourceImpl @Inject constructor(
    private val serviceApi: RecipeServiceApi
) : AddUpdateRecipeRemoteDataSource {
    override suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimpleResponseModel> {
        return try {
            val request = addUpdateRecipeRequestModel.toAddUpdateRecipeRequest()
            val response = serviceApi.addRecipe(request)

            if (response.isSuccessful) {
                ServiceResult.Success(response.toSimplesResponseModel())
            } else {
                ServiceResult.Error(response.message)
            }

        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimpleResponseModel> {
        return try {
            val request = addUpdateRecipeRequestModel.toAddUpdateRecipeRequest()
            val response = serviceApi.updateRecipe(recipeId = recipeId, recipeRequest = request)

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