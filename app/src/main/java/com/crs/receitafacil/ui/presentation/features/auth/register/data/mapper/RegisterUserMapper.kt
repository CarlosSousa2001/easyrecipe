package com.crs.receitafacil.ui.presentation.features.auth.register.data.mapper

import com.crs.receitafacil.core.data.remote.request.AddUserRequest
import com.crs.receitafacil.core.data.remote.response.SimpleResponse
import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel

fun AddUserRequestModel.toAddUserRequest() : AddUserRequest {
    return AddUserRequest(name, email, password, phone)
}

fun SimpleResponse.toSimplesResponseModel(): SimpleResponseModel{
    return SimpleResponseModel(isSuccessful, message)
}