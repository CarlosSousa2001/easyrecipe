package com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase

import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeInputValidationType

interface ValidateAddUpdateRecipeInputUseCase {

    operator fun invoke(parameters: Parameters): AddUpdateRecipeInputValidationType
    data class Parameters(
        val name: String,
        val category: String,
        val preparationTime: String,
        val preparationMode: String
    )
}


class ValidateAddUpdateRecipeInputUseCaseImpl : ValidateAddUpdateRecipeInputUseCase {
    override fun invoke(parameters: ValidateAddUpdateRecipeInputUseCase.Parameters): AddUpdateRecipeInputValidationType {
        return if (parameters.name.isEmpty() || parameters.category.isEmpty() ||
            parameters.preparationTime.isEmpty() || parameters.preparationMode.isEmpty()
        ) {
            AddUpdateRecipeInputValidationType.EmptyField

        } else {
            AddUpdateRecipeInputValidationType.Valid
        }
    }
}