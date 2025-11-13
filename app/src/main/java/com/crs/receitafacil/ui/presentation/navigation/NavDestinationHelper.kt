package com.crs.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun NavDestinationHelper(
    shouldNavigate: () -> Boolean,
    destination: () -> Unit,
) {
    LaunchedEffect(key1 = shouldNavigate()) {
        if (shouldNavigate()) {
            destination()
        }
    }
}