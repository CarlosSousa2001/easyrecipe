package com.crs.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesScreen
import com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesViewModel
import com.crs.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.recipesScreen(
    onNavigateToAuthGraph: () -> Unit = {},

    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
    onNavigationToRecipeDetailsScreen: (recipeId: String) -> Unit
) {
    composable<HomeScreens.RecipesScreen> {

        val viewModel: RecipesViewModel = hiltViewModel<RecipesViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()

        RecipesScreen(
            uiState = uiState,
            selectedCategory = selectedCategory,
            onLogout = {
                viewModel.logout()
                onNavigateToAuthGraph()
            },
            onSelectedCategory = { viewModel.selectedCategory(it) },
            onNavigationToProfileScreen = onNavigationToProfileScreen,
            onNavigationToSearchScreen = onNavigationToSearchScreen,
            onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen,
            onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen,
            onNavigationToRecipeDetailsScreen = { onNavigationToRecipeDetailsScreen(it) },
        )
    }
}

fun NavController.navigateToRecipesScreen(){
    navigate(HomeScreens.RecipesScreen) {
        popUpTo(0)
    }
}
