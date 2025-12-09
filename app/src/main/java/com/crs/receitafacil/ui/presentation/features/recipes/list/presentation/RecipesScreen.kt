package com.crs.receitafacil.ui.presentation.features.recipes.list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.crs.receitafacil.ui.presentation.components.bottombar.BottomBar
import com.crs.receitafacil.ui.presentation.components.topbar.CommonTopBar
import com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.components.RecipeContent
import com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.states.RecipesUIState
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme

@Composable
fun RecipesScreen(
    uiState: RecipesUIState,
    selectedCategory: Int?,
    onLogout: () -> Unit,
    onSelectedCategory: (Int?) -> Unit,
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
    onNavigationToRecipeDetailsScreen: (recipeId: String) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                title = "Olá ${uiState.userName}",
                actionImageVector = Icons.AutoMirrored.Outlined.Logout,
                onActionIconButton = { onLogout() },
                enable = true,
                navigationImageVector = null,
                contentDescription = "",
                onNavigationIconButton = {},
            )
        },
        bottomBar = {
            BottomBar(
                onNavigationToProfileScreen = onNavigationToProfileScreen,
                onNavigationToSearchScreen = onNavigationToSearchScreen,
                onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen,
                onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                RecipeContent(
                    isEmpty = uiState.isEmpty,
                    isLoading = uiState.isLoading,
                    errorMessage = uiState.errorMessage,
                    selectedCategory = selectedCategory,
                    onSelectedCategory = {
                        onSelectedCategory(it)
                    },
                    recipes = uiState.recipes,
                    onNavigateToRecipeDetailsScreen = onNavigationToRecipeDetailsScreen
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun RecipesScreenPreview() {
    ReceitaFacilTheme {
        RecipesScreen(
            uiState = RecipesUIState(),
            selectedCategory = 1,
            onLogout = {},
            onSelectedCategory = {},
            onNavigationToProfileScreen = {},
            onNavigationToSearchScreen = {},
            onNavigationToAddRecipeScreen = {},
            onNavigationToUsersConnectionScreen = {},
            onNavigationToRecipeDetailsScreen = {}
        )
    }
}