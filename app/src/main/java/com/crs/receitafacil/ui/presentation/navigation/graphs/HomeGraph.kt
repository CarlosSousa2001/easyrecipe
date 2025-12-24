package com.crs.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.crs.receitafacil.ui.presentation.navigation.addUpdateRecipeScreen
import com.crs.receitafacil.ui.presentation.navigation.recipesScreen
import com.crs.receitafacil.ui.presentation.navigation.screens.Graphs
import com.crs.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph(
    onNavigateUp: () -> Unit,

    onNavigateToAuthGraph: () -> Unit = {},
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: (String?) -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
    onNavigationToRecipeDetailsScreen: (recipeId: String) -> Unit,
    onNavigateToRecipesScreen: () -> Unit
) {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.RecipesScreen
    ) {
        recipesScreen(
            onNavigateToAuthGraph = onNavigateToAuthGraph,
            onNavigationToProfileScreen = onNavigationToProfileScreen,
            onNavigationToSearchScreen = onNavigationToSearchScreen,
            onNavigationToAddRecipeScreen = { onNavigationToAddRecipeScreen("") },
            onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen,
            onNavigationToRecipeDetailsScreen = { onNavigationToRecipeDetailsScreen(it) },
        )

        addUpdateRecipeScreen(
            onNavigateUp = onNavigateUp,
            onNavigateToRecipesScreen = onNavigateToRecipesScreen
        )
    }
}

fun NavController.navigationToHomeGraph(
    navOptions: NavOptions? = null
) {
    navigate(Graphs.HomeGraph, navOptions)
}
