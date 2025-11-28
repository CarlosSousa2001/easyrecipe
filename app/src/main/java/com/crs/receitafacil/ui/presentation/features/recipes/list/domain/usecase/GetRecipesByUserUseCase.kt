package com.crs.receitafacil.ui.presentation.features.recipes.list.domain.usecase

import com.crs.receitafacil.core.domain.model.RecipesResponseModel
import com.crs.receitafacil.core.utils.ResponseData
import com.crs.receitafacil.core.utils.ServiceResult
import com.crs.receitafacil.core.utils.Task
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetRecipesByUserUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<List<RecipesResponseModel>>>
    data class Parameters(val category: Int?)
}

class GetRecipesByUserUseCaseImpl @Inject constructor(
    private val getRecipesByUserRepository: GetRecipesByUserRepository
) : GetRecipesByUserUseCase,
    Task<GetRecipesByUserUseCase.Parameters, List<RecipesResponseModel>>() {
    override suspend fun executeTask(parameters: GetRecipesByUserUseCase.Parameters): ResponseData<List<RecipesResponseModel>> {
        return try {
            when (val response = getRecipesByUserRepository.getRecipesByUser(parameters.category)) {
                is ServiceResult.Success -> {
                    if(response.data.isEmpty()){
                        ResponseData.Empty
                    } else {
                        ResponseData.Success(response.data)
                    }
                }
                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }

            }
        } catch (e: Throwable) {
            return ResponseData.Error(e)
        }
    }
}
