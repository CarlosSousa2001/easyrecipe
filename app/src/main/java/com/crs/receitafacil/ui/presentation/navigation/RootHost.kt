package com.crs.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.crs.receitafacil.ui.presentation.navigation.graphs.authGraph
import com.crs.receitafacil.ui.presentation.navigation.graphs.homeGraph
import com.crs.receitafacil.ui.presentation.navigation.graphs.navigationToHomeGraph
import com.crs.receitafacil.ui.presentation.navigation.screens.Graphs

@Composable
fun RootHost(
    startDestination: Graphs,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            onNavigateToHomeGraph = { navOptions ->
                navController.navigationToHomeGraph(navOptions)
            },
            onNavigateRegisterScreen = {
                navController.navigateToRegisterScreen()
            },
            onNavigateLoginScreen = {
                navController.navigateToLoginScreen()
            }

        )
        homeGraph(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToAuthGraph = {
                navController.navigateToLoginScreen()
            },
            onNavigationToProfileScreen = {},
            onNavigationToSearchScreen = {},
            onNavigationToAddRecipeScreen = {
                navController.navigateToAddUpdateRecipeScreen(it)
            },
            onNavigationToUsersConnectionScreen = {},
            onNavigationToRecipeDetailsScreen = {},
            onNavigateToRecipesScreen = {
                navController.navigateToRecipesScreen()
            }
        )
    }

}