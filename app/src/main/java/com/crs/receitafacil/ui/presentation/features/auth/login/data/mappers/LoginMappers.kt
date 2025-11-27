package com.crs.receitafacil.ui.presentation.features.auth.login.data.mappers

import com.crs.receitafacil.core.data.remote.request.AuthUserRequest
import com.crs.receitafacil.core.data.remote.response.TokenResponse
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

fun AuthUserRequestModel.toAuthUserRequest(): AuthUserRequest {
    return AuthUserRequest(email, password)
}

fun TokenResponse.toTokenResponseModel(): TokenResponseModel {
    return TokenResponseModel(isSuccessful, message, token, userName)

}