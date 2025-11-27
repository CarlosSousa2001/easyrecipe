package com.crs.receitafacil.ui.presentation.features.auth.login.domain.source

import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

interface LoginRemoteDataSource {
    suspend fun login(authUserRequestModel: AuthUserRequestModel) : ServiceResult<TokenResponseModel>
}