package com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase

import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeInputValidationType

interface ValidateDialogInputUseCase {
    operator fun invoke(parameters: Parameters): AddUpdateRecipeInputValidationType
    data class Parameters(
        val ingredientsProductName: String,
        val ingredientProductQuantity: String
    )
}

class ValidateDialogInputUseCaseImpl : ValidateDialogInputUseCase {
    override fun invoke(parameters: ValidateDialogInputUseCase.Parameters): AddUpdateRecipeInputValidationType {
        return if (parameters.ingredientsProductName.isEmpty() || parameters.ingredientProductQuantity.isEmpty()) {
            AddUpdateRecipeInputValidationType.EmptyField

        } else {
            AddUpdateRecipeInputValidationType.Valid
        }
    }
}