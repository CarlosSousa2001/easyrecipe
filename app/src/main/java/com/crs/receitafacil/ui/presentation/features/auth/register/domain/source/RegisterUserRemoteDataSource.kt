package com.crs.receitafacil.ui.presentation.features.auth.register.domain.source

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel

interface RegisterUserRemoteDataSource {
    suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel>
}