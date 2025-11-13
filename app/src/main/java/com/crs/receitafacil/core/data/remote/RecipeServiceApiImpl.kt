package com.crs.receitafacil.core.data.remote

import com.crs.receitafacil.core.data.remote.request.AddUserRequest
import com.crs.receitafacil.core.data.remote.request.AuthUserRequest
import com.crs.receitafacil.core.data.remote.response.SimpleResponse
import com.crs.receitafacil.core.data.remote.response.TokenResponse
import com.crs.receitafacil.core.data.remote.response.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RecipeServiceApiImpl @Inject constructor(
    private val client: HttpClient
): RecipeServiceApi {
    override suspend fun login(authUserRequest: AuthUserRequest): TokenResponse {
        val response = client.post("users/login") {
            contentType(ContentType.Application.Json)
            setBody(authUserRequest)
        }
        return response.body()
    }

    override suspend fun register(addUserRequest: AddUserRequest): SimpleResponse {
        val response = client.post("users/register") {
            contentType(ContentType.Application.Json)
            setBody(addUserRequest)
        }
        return response.body()
    }

    override suspend fun getProfile(): UserResponse {
        val response = client.get("users/profile") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }
}