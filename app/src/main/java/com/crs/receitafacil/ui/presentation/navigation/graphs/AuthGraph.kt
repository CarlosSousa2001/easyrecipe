package com.crs.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.crs.receitafacil.ui.presentation.navigation.loginScreen
import com.crs.receitafacil.ui.presentation.navigation.registerScreen
import com.crs.receitafacil.ui.presentation.navigation.screens.AuthScreens
import com.crs.receitafacil.ui.presentation.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToHomeGraph: (NavOptions) -> Unit,
    onNavigateLoginScreen: () -> Unit,
    onNavigateRegisterScreen: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen,
    ) {
        loginScreen(
            onNavigateToHomeGraph = {
                onNavigateToHomeGraph(navOptions {
                    popUpTo(Graphs.AuthGraph)
                })
            },
            onNavigateRegisterScreen = onNavigateRegisterScreen
        )

        registerScreen(
            onNavigateLoginScreen = onNavigateLoginScreen
        )
    }
}