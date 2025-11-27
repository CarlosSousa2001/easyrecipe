package com.crs.receitafacil.ui.presentation.features.auth.register.domain.repository

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel

interface RegisterUserRepository {
    suspend fun registerUser(addUserRequestModel: AddUserRequestModel): ServiceResult<SimpleResponseModel>
}