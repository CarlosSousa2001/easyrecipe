package com.crs.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.receitafacil.ui.presentation.features.auth.login.presentation.LoginScreen
import com.crs.receitafacil.ui.presentation.features.auth.login.presentation.LoginViewModel
import com.crs.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.loginScreen(
    onNavigateToHomeGraph: () -> Unit,
    onNavigateRegisterScreen: () -> Unit
) {
    composable<AuthScreens.LoginScreen> {

        val viewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel

        LoginScreen(
            uiState = uiState,
            sideEffectFlow = sideEffectFlow,
            onEvent = viewModel::onEvent,
            onNavigateToRegisterScreen = onNavigateRegisterScreen,
            onNavigateToHomeGraph = onNavigateToHomeGraph
        )
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
//    LoginScreen(onNavigateToRegisterScreen = {})
}

