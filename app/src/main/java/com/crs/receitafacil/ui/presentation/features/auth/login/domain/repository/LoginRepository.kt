package com.crs.receitafacil.ui.presentation.features.auth.login.domain.repository

import com.crs.receitafacil.core.domain.model.UserData
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(authUserRequestModel: AuthUserRequestModel) : ServiceResult<TokenResponseModel>
    fun getData(): Flow<UserData>
    suspend fun saveData(token: String, userName: String)
    suspend fun clearAll()
}