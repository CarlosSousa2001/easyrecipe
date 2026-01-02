package com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase

import com.crs.receitafacil.core.utils.ResponseData
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.core.utils.Task
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LoginUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<TokenResponseModel>>
    data class Parameters(val authUserRequestModel: AuthUserRequestModel)
}

class LoginUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUseCase, Task<LoginUseCase.Parameters, TokenResponseModel>() {
    override suspend fun executeTask(parameters: LoginUseCase.Parameters): ResponseData<TokenResponseModel> {
        return try {
            when (val response = loginRepository.login(parameters.authUserRequestModel)) {
                is ServiceResult.Success -> {
                    ResponseData.Success(response.data)
                }

                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }
}