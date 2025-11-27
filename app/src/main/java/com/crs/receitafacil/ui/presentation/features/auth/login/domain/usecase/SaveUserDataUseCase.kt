package com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase

import com.crs.receitafacil.core.utils.ResponseData
import com.crs.receitafacil.core.utils.Task
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SaveUserDataUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<Unit>>
    data class Parameters(val token: String, val userName: String)
}

class SaveUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : SaveUserDataUseCase, Task<SaveUserDataUseCase.Parameters, Unit>() {
    override suspend fun executeTask(parameters: SaveUserDataUseCase.Parameters): ResponseData<Unit> {
        return try {
            ResponseData.Success(
                loginRepository.saveData(
                    parameters.token,
                    parameters.userName
                )
            )
        } catch (e: Throwable) {
            ResponseData.Error(e)
        }
    }

}
