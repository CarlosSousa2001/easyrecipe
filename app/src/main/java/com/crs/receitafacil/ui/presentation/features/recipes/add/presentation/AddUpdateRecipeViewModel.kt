package com.crs.receitafacil.ui.presentation.features.recipes.add.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.receitafacil.core.domain.model.CategoryEnum
import com.crs.receitafacil.core.domain.model.IngredientsModel
import com.crs.receitafacil.core.sideeffects.SideEffect
import com.crs.receitafacil.core.utils.extensions.observerState
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeInputValidationType
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.AddRecipeUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.UpdateRecipeUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.ValidateAddUpdateRecipeInputUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.domain.usecase.ValidateDialogInputUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.state.AddUpdateRecipeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddUpdateRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
    private val validateAddUpdateRecipeInputUseCase: ValidateAddUpdateRecipeInputUseCase,
    private val validateDialogInputUseCase: ValidateDialogInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddUpdateRecipeUIState())
    var uiState: StateFlow<AddUpdateRecipeUIState> = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    var sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    var isAddIngredientDialogShown by mutableStateOf(false)
        private set
    var ingredients = mutableStateListOf<IngredientsModel>()

    fun onEvent(event: AddUpdateRecipeEvent) {
        when (event) {
            is AddUpdateRecipeEvent.OnNameInputChange -> {
                _uiState.update { it.copy(nameInput = event.name) }
                checkInputValidation()
            }

            is AddUpdateRecipeEvent.OnCategoryInputChange -> {
                _uiState.update { it.copy(categoryInput = event.category) }
                checkInputValidation()
            }

            is AddUpdateRecipeEvent.OnPreparationModeInputChange -> {
                _uiState.update { it.copy(preparationModeInput = event.preparationMode) }
                checkInputValidation()
            }

            is AddUpdateRecipeEvent.OnPreparationTimeInputChange -> {
                _uiState.update { it.copy(preparationTimeInput = event.preparationTime) }
                checkInputValidation()
            }

            is AddUpdateRecipeEvent.OnIngredientProductNameInputChange -> {
                _uiState.update { it.copy(ingredientProductNameInput = event.ingredientProductName) }
                checkInputDialogValidation()

            }

            is AddUpdateRecipeEvent.OnIngredientProductQuantityInputChange -> {
                _uiState.update { it.copy(ingredientProductQuantityInput = event.ingredientProductQuantity) }
                checkInputDialogValidation()
            }

            is AddUpdateRecipeEvent.OnRemoveIngredient -> {
                ingredients.remove(event.ingredient)
            }

            AddUpdateRecipeEvent.OnAddIngredient -> {
                addIngredient()
            }

            AddUpdateRecipeEvent.OnAddOrUpdateRecipe -> {
                addOrUpdateRecipe()
            }
        }
    }


    fun onDismissDialog() {
        isAddIngredientDialogShown = false
    }

    fun onOpenDialog() {
        isAddIngredientDialogShown = true
    }

    private fun clearFields() {
        _uiState.update {
            it.copy(
                ingredientProductNameInput = "",
                ingredientProductQuantityInput = ""
            )
        }
    }

    private fun addIngredient() {
        if (_uiState.value.isInputDialogValid) {
            val id = UUID.randomUUID().toString()
            val productName = _uiState.value.ingredientProductNameInput
            val productQuantity = _uiState.value.ingredientProductQuantityInput
            val ingredient = IngredientsModel(id, productName, productQuantity)
            ingredients.add(ingredient)
            onDismissDialog()
            clearFields()
        } else {
            return
        }
    }

    private fun addOrUpdateRecipe() {
        if (_uiState.value.currentRecipeId.isEmpty()) {
            addRecipe()
        } else {
            updateRecipe()
        }
    }

    private fun addRecipe() {
        viewModelScope.launch {
            addRecipeUseCase.invoke(
                AddRecipeUseCase.Parameters(
                    AddUpdateRecipeRequestModel(
                        name = _uiState.value.nameInput,
                        category = CategoryEnum.fromDescription(_uiState.value.categoryInput)?.value
                            ?: 0,
                        preparationTime = _uiState.value.preparationTimeInput,
                        preparationMode = _uiState.value.preparationModeInput,
                        ingredients = ingredients,
                    )
                )
            ).observerState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageRegisterProcess = error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isOperationSuccessFull = response.isSuccessful
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message))
                }
            )
        }
    }

    private fun updateRecipe() {
        viewModelScope.launch {
            updateRecipeUseCase.invoke(
                UpdateRecipeUseCase.Parameters(
                    recipeId = _uiState.value.currentRecipeId,
                    addUpdateRecipeRequestModel = AddUpdateRecipeRequestModel(
                        name = _uiState.value.nameInput,
                        category = CategoryEnum.fromDescription(_uiState.value.categoryInput)?.value
                            ?: 0,
                        preparationTime = _uiState.value.preparationTimeInput,
                        preparationMode = _uiState.value.preparationModeInput,
                        ingredients = ingredients,
                    ),

                    )
            ).observerState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageRegisterProcess = error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isOperationSuccessFull = response.isSuccessful
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message))
                }
            )
        }
    }


    private fun checkInputValidation() {
        val validationResult = validateAddUpdateRecipeInputUseCase.invoke(
            parameters = ValidateAddUpdateRecipeInputUseCase.Parameters(
                name = _uiState.value.nameInput,
                category = _uiState.value.categoryInput,
                preparationTime = _uiState.value.preparationTimeInput,
                preparationMode = _uiState.value.preparationModeInput
            )
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: AddUpdateRecipeInputValidationType) {
        _uiState.update {
            when (type) {
                AddUpdateRecipeInputValidationType.EmptyField -> {
                    it.copy(
                        errorMessageInput = "Preencha todos os dados",
                        isInputValid = false
                    )
                }

                AddUpdateRecipeInputValidationType.Valid -> {
                    it.copy(
                        errorMessageInput = null,
                        isInputValid = true
                    )
                }

                else -> {
                    it.copy(
                        errorMessageInput = null,
                        isInputValid = true
                    )
                }
            }
        }
    }

    private fun checkInputDialogValidation() {
        val validationResult = validateDialogInputUseCase.invoke(
            parameters = ValidateDialogInputUseCase.Parameters(
                ingredientsProductName = _uiState.value.ingredientProductNameInput,
                ingredientProductQuantity = _uiState.value.ingredientProductQuantityInput
            )
        )

        processInputDialogValidationType(validationResult)

    }

    private fun processInputDialogValidationType(type: AddUpdateRecipeInputValidationType) {
        _uiState.update {
            when (type) {
                AddUpdateRecipeInputValidationType.EmptyDialogField -> {
                    it.copy(
                        errorMessageDialogInput = "Preencha todos os dados",
                        isInputDialogValid = false
                    )
                }

                AddUpdateRecipeInputValidationType.Valid -> {
                    it.copy(
                        errorMessageDialogInput = null,
                        isInputDialogValid = true
                    )
                }

                else -> {
                    it.copy(
                        errorMessageDialogInput = null,
                        isInputDialogValid = true
                    )
                }
            }
        }
    }

}