package com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.states

import com.crs.receitafacil.core.domain.model.RecipesResponseModel

data class RecipesUIState(
    val userName: String? = null,
    val category: String? = null,
    val recipes: List<RecipesResponseModel> = emptyList(),
    val isEmpty: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
