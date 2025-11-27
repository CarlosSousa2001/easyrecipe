package com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase

import com.crs.receitafacil.core.utils.FlowTask
import com.crs.receitafacil.core.utils.ResponseData
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoveUserDataUseCase {
    suspend operator fun invoke(parameters: Unit = Unit): Flow<ResponseData<Unit>>
}

class RemoveUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : RemoveUserDataUseCase, FlowTask<Unit, ResponseData<Unit>>() {
    override suspend fun executeTaskFlow(parameters: Unit): Flow<ResponseData<Unit>> {
        return flow {
            try {
                emit(ResponseData.Success(loginRepository.clearAll()))
            } catch (e: Throwable) {
                emit(ResponseData.Error(e))
            }
        }
    }

}