package com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase

import com.crs.receitafacil.core.domain.model.SimpleResponseModel
import com.crs.receitafacil.core.utils.ResponseData
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.core.utils.Task
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.repository.AddUpdateRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UpdateRecipeUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimpleResponseModel>>
    data class Parameters(
        val recipeId: String,
        val addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    )
}


class UpdateRecipeUseCaseImpl @Inject constructor(
    private val addUpdateRecipeRepository: AddUpdateRecipeRepository
) : UpdateRecipeUseCase, Task<UpdateRecipeUseCase.Parameters, SimpleResponseModel>() {
    override suspend fun executeTask(parameters: UpdateRecipeUseCase.Parameters): ResponseData<SimpleResponseModel> {
        return try {
            when (val response =
                addUpdateRecipeRepository.updateRecipe(
                    recipeId = parameters.recipeId,
                    addUpdateRecipeRequestModel = parameters.addUpdateRecipeRequestModel
                )) {
                is ServiceResult.Success -> ResponseData.Success(response.data)
                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }
            }
        } catch (e: Exception) {
            return ResponseData.Error(e)
        }
    }
}