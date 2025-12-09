package com.crs.receitafacil.ui.presentation.features.recipes.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.receitafacil.core.utils.extensions.observerState
import com.crs.receitafacil.core.utils.logging.logInfo
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.GetUserDataUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.RemoveUserDataUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.list.domain.usecase.GetRecipesByUserUseCase
import com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.states.RecipesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesByUserUseCase: GetRecipesByUserUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val removeUserDataUseCase: RemoveUserDataUseCase
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow<Int?>(null)
    var selectedCategory: StateFlow<Int?> = _selectedCategory.asStateFlow()
    private val _uiState = MutableStateFlow(value = RecipesUIState())
    var uiState: StateFlow<RecipesUIState> = _uiState.onStart {
        fetchRecipes(null)
        fetchUserName()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RecipesUIState()
    )

    fun selectedCategory(category: Int?) {
        _selectedCategory.value = category
        fetchRecipes(_selectedCategory.value)
    }

    fun logout() {
        viewModelScope.launch {
            removeUserDataUseCase.invoke()
                .observerState(
                    onLoading = {},
                    onEmpty = {},
                    onSuccess = {
                        logInfo("REMOVE_TOKEN", "Token removido com sucesso")
                    },
                    onFailure = {}
                )
        }
    }

    private fun fetchUserName() {
        viewModelScope.launch {
            getUserDataUseCase.invoke().collect { userData ->
                _uiState.update { it.copy(userName = userData.userName) }
            }
        }
    }

    private fun fetchRecipes(category: Int?) {
        viewModelScope.launch {
            getRecipesByUserUseCase.invoke(
                parameters = GetRecipesByUserUseCase.Parameters(category = category)
            ).observerState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true, isEmpty = false) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isEmpty = false,
                            errorMessage = error.message
                        )
                    }
                },
                onEmpty = {
                    _uiState.update { it.copy(isLoading = false, isEmpty = true) }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isEmpty = false,
                            recipes = response
                        )
                    }
                }
            )
        }
    }


}