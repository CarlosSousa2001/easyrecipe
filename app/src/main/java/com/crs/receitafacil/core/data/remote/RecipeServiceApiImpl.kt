package com.crs.receitafacil.core.data.remote

import com.crs.receitafacil.core.data.remote.request.AddUpdateRecipeRequest
import com.crs.receitafacil.core.data.remote.request.AddUserRequest
import com.crs.receitafacil.core.data.remote.request.AuthUserRequest
import com.crs.receitafacil.core.data.remote.response.RecipesDetailsResponse
import com.crs.receitafacil.core.data.remote.response.RecipesResponse
import com.crs.receitafacil.core.data.remote.response.SimpleResponse
import com.crs.receitafacil.core.data.remote.response.TokenResponse
import com.crs.receitafacil.core.data.remote.response.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RecipeServiceApiImpl @Inject constructor(
    private val client: HttpClient
) : RecipeServiceApi {
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

    override suspend fun getRecipesByUser(category: Int?): List<RecipesResponse> {
        val response: HttpResponse = client.get(urlString = "recipes") {
            contentType(ContentType.Application.Json)
            category?.let { parameter("category", it) }
        }
        return response.body()
    }

    override suspend fun searchRecipes(nameOrIngredient: String): List<RecipesResponse> {
        val response: HttpResponse = client.get(urlString = "recipes/search") {
            contentType(ContentType.Application.Json)
            parameter("nameOrIngredient", nameOrIngredient)
        }
        return response.body()
    }

    override suspend fun getRecipeById(recipeId: String): RecipesDetailsResponse {
        val response: HttpResponse = client.get(urlString = "recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }

    override suspend fun addRecipe(recipeRequest: AddUpdateRecipeRequest): SimpleResponse {
        val response: HttpResponse = client.post(urlString = "recipes") {
            contentType(ContentType.Application.Json)
            setBody(recipeRequest)
        }
        return response.body()
    }

    override suspend fun updateRecipe(
        recipeId: String,
        recipeRequest: AddUpdateRecipeRequest
    ): SimpleResponse {
        val response: HttpResponse = client.put(urlString = "recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
            setBody(recipeRequest)
        }
        return response.body()
    }

    override suspend fun deleteRecipe(recipeId: String): SimpleResponse {
        val response: HttpResponse = client.delete(urlString = "recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }
}