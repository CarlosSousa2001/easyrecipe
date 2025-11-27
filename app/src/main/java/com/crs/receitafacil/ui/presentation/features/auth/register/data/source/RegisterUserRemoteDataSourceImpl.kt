package com.crs.receitafacil.ui.presentation.features.auth.register.data.source

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.domain.exceptions.ErrorResponseException
import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.register.data.mapper.toAddUserRequest
import com.crs.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimplesResponseModel
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.source.RegisterUserRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class RegisterUserRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceApi: RecipeServiceApi
) : RegisterUserRemoteDataSource {
    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel> {
        return try {
            val addUserRequest = addUserRequestModel.toAddUserRequest()

            val response = recipesServiceApi.register(addUserRequest)

            if (!response.isSuccessful) {
                ServiceResult.Error(response.message)
            }

            ServiceResult.Success(response.toSimplesResponseModel())

        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}