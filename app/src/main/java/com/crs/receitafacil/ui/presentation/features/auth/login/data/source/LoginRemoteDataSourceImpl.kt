package com.crs.receitafacil.ui.presentation.features.auth.login.data.source

import com.crs.receitafacil.core.data.remote.RecipeServiceApi
import com.crs.receitafacil.core.domain.exceptions.ErrorResponseException
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.login.data.mappers.toAuthUserRequest
import com.crs.receitafacil.ui.presentation.features.auth.login.data.mappers.toTokenResponseModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val recipeServiceApi: RecipeServiceApi
) : LoginRemoteDataSource {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return try {
            val response = recipeServiceApi.login(authUserRequestModel.toAuthUserRequest())

            if (!response.isSuccessful) {
                ServiceResult.Error(message = response.message)
            }

            ServiceResult.Success(response.toTokenResponseModel())

        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}
