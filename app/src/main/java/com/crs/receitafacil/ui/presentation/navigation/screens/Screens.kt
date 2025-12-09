package com.crs.receitafacil.ui.presentation.navigation.screens

import kotlinx.serialization.Serializable

@Serializable
sealed interface Graphs {
    @Serializable
    data object AuthGraph : Graphs

    @Serializable
    data object HomeGraph : Graphs
}

@Serializable
sealed interface AuthScreens {
    @Serializable
    data object LoginScreen : AuthScreens

    @Serializable
    data object RegisterScreen : AuthScreens
}

@Serializable
sealed interface HomeScreens {

    @Serializable
    data class AddRecipeScreen(val recipeId: String? = "") : HomeScreens

    @Serializable
    data class RecipeDetailsScreen(val recipeId: String) : HomeScreens

    @Serializable
    data class WebSocketUpdateScreen(val qrcode: String?) : HomeScreens

    @Serializable
    data object RecipesScreen : HomeScreens

    @Serializable
    data object ProfileScreen : HomeScreens

    @Serializable
    data object CreateQrCodeScreen : HomeScreens

    @Serializable
    data object ReadQrCodeScreen : HomeScreens

    @Serializable
    data object UsersConnectionsScreen : HomeScreens

    @Serializable
    data object Search : HomeScreens

}
