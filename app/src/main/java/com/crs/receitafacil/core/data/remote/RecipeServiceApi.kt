package com.crs.receitafacil.core.data.remote

import com.crs.receitafacil.core.data.remote.request.AddUpdateRecipeRequest
import com.crs.receitafacil.core.data.remote.request.AddUserRequest
import com.crs.receitafacil.core.data.remote.request.AuthUserRequest
import com.crs.receitafacil.core.data.remote.response.RecipesDetailsResponse
import com.crs.receitafacil.core.data.remote.response.RecipesResponse
import com.crs.receitafacil.core.data.remote.response.SimpleResponse
import com.crs.receitafacil.core.data.remote.response.TokenResponse
import com.crs.receitafacil.core.data.remote.response.UserResponse

interface RecipeServiceApi {
    suspend fun login(authUserRequest: AuthUserRequest): TokenResponse
    suspend fun register(addUserRequest: AddUserRequest): SimpleResponse
    suspend fun getProfile(): UserResponse

    suspend fun getRecipesByUser(category: Int?): List<RecipesResponse>
    suspend fun searchRecipes(nameOrIngredient: String): List<RecipesResponse>
    suspend fun getRecipeById(recipeId: String): RecipesDetailsResponse
    suspend fun addRecipe(recipeRequest: AddUpdateRecipeRequest): SimpleResponse
    suspend fun updateRecipe(recipeId: String, recipeRequest: AddUpdateRecipeRequest): SimpleResponse
    suspend fun deleteRecipe(recipeId: String): SimpleResponse
}
