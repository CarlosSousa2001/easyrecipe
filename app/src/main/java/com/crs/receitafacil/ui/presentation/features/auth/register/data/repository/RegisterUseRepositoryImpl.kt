package com.crs.receitafacil.ui.presentation.features.auth.register.data.repository

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.DispatchersProvider
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.repository.RegisterUserRepository
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.source.RegisterUserRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUseRepositoryImpl @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource,
    private val dispatchersProvider: DispatchersProvider
) : RegisterUserRepository {
    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel> {
        return withContext(dispatchersProvider.io()) {
            remoteDataSource.registerUser(addUserRequestModel)
        }
    }
}