package com.crs.receitafacil.core.data.remote

import com.crs.receitafacil.core.data.remote.request.AddUserRequest
import com.crs.receitafacil.core.data.remote.request.AuthUserRequest
import com.crs.receitafacil.core.data.remote.response.SimpleResponse
import com.crs.receitafacil.core.data.remote.response.TokenResponse
import com.crs.receitafacil.core.data.remote.response.UserResponse

interface RecipeServiceApi {
    suspend fun login(authUserRequest: AuthUserRequest): TokenResponse
    suspend fun register(addUserRequest: AddUserRequest): SimpleResponse
    suspend fun getProfile(): UserResponse
}
