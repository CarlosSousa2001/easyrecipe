package com.crs.receitafacil.ui.presentation.features.register.data.repository

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.register.domain.model.AddUserRequestModel
import com.crs.receitafacil.ui.presentation.features.register.domain.repository.RegisterUserRepository
import com.crs.receitafacil.ui.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import javax.inject.Inject

class RegisterUseRepositoryImpl @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource
): RegisterUserRepository {
    override suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel> {
       return remoteDataSource.registerUser(addUserRequestModel)
    }
}