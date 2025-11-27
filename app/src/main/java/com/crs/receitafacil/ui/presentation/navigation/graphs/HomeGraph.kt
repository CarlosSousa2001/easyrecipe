package com.crs.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.crs.receitafacil.ui.presentation.navigation.homeScreen
import com.crs.receitafacil.ui.presentation.navigation.screens.AuthScreens
import com.crs.receitafacil.ui.presentation.navigation.screens.Graphs
import com.crs.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph(
    onNavigateUp: () -> Unit,
) {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.HomeScreen
    ) {
        homeScreen()
    }
}

fun NavController.navigationToHomeGraph(
    navOptions: NavOptions? = null
){
    navigate(Graphs.HomeGraph, navOptions)
}
