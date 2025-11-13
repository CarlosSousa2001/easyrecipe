package com.crs.receitafacil.ui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.loginScreen(
    onNavigateToHomeGraph: () -> Unit,
    onNavigateRegisterScreen: () -> Unit
) {
    composable<AuthScreens.LoginScreen> {
        LoginScreen(
            onNavigateToRegisterScreen = onNavigateRegisterScreen
        )
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegisterScreen: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onNavigateToRegisterScreen()
            }
        ) {
            Text(text = "Login")
        }
    }
}

fun NavController.navigateToLoginScreen(){
    navigate(AuthScreens.LoginScreen){
        popUpTo(0)
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(onNavigateToRegisterScreen = {})
}

