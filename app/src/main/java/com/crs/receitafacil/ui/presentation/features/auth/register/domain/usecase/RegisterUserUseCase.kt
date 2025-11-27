package com.crs.receitafacil.ui.presentation.features.auth.register.domain.usecase

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.core.utils.ResponseData
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.core.utils.Task
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.repository.RegisterUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RegisterUserUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimpleResponseModel>>
    data class Parameters(val addUserRequestModel: AddUserRequestModel)
}

class RegisterUserUseCaseImpl @Inject constructor(
    private val registerUserRepository: RegisterUserRepository,
) : RegisterUserUseCase, Task<RegisterUserUseCase.Parameters, SimpleResponseModel>() {

    override suspend fun executeTask(parameters: RegisterUserUseCase.Parameters): ResponseData<SimpleResponseModel> {
        return try {
                when (val response =
                    registerUserRepository.registerUser(parameters.addUserRequestModel)) {
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