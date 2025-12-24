package com.crs.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.AddUpdateRecipeViewModel
import com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.AddUpdateRecipesScreen
import com.crs.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.addUpdateRecipeScreen(
    onNavigateUp: () -> Unit,
    onNavigateToRecipesScreen: () -> Unit
) {
    composable<HomeScreens.AddRecipeScreen> {

        val viewModel: AddUpdateRecipeViewModel = hiltViewModel<AddUpdateRecipeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel
        val addIngredientDialogShown = viewModel.isAddIngredientDialogShown
        val ingredients = viewModel.ingredients
        val buttonEnabled = uiState.isInputValid && ingredients.isNotEmpty()

        AddUpdateRecipesScreen(
            uiState = uiState,
            buttonEnabled = buttonEnabled,
            sideEffectFlow = sideEffectFlow,
            addIngredientDialogShow = addIngredientDialogShown,
            ingredients = ingredients,
            onDismiss = viewModel::onDismissDialog,
            onOpenDialog = viewModel::onOpenDialog,
            onNavigateUp = onNavigateUp,
            onNavigateToRecipesScreen = onNavigateToRecipesScreen,
            onEvent = viewModel::onEvent
        )
    }
}

fun NavController.navigateToAddUpdateRecipeScreen(recipeId: String? = null){
    navigate(HomeScreens.AddRecipeScreen(recipeId = recipeId))
}
