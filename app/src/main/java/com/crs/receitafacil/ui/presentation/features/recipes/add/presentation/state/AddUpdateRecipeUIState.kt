package com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.state

data class AddUpdateRecipeUIState(
    val currentRecipeId: String = "",
    val nameInput: String = "",
    val categoryInput: String = "",
    val preparationTimeInput: String = "",
    val preparationModeInput: String = "",
    val ingredientsInput: String = "",
    val ingredientProductNameInput: String = "",
    val ingredientProductQuantityInput: String = "",
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    val isInputDialogValid: Boolean = false,
    val errorMessage: String? = null,
    val errorMessageInput: String? = null,
    val errorMessageDialogInput: String? = null,
    val isOperationSuccessFull: Boolean = false,
    val errorMessageRegisterProcess: String? = null
)

