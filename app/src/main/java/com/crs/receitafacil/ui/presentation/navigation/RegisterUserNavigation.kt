package com.crs.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.crs.receitafacil.ui.presentation.features.auth.register.presentation.RegisterScreen
import com.crs.receitafacil.ui.presentation.features.auth.register.presentation.RegisterUserViewModel
import com.crs.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.registerScreen(
    onNavigateLoginScreen: () -> Unit
) {
    composable<AuthScreens.RegisterScreen> {
        val viewModel: RegisterUserViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel
        RegisterScreen(
            uiState = uiState,
            sideEffectFlow = sideEffectFlow,
            onEvent = { viewModel.onEvent(it) },
            onNameChanged = { viewModel.onNameInputChange(it) },
            onEmailChanged = { viewModel.onEmailInputChange(it) },
            onPhoneChanged = { viewModel.onPhoneInputChange(it) },
            onPasswordChanged = { viewModel.onPasswordInputChange(it) },
            onPasswordRepeatedChanged = { viewModel.onPasswordRepeatInputChange(it) },
            onToggleVisualTransformationPassword = { viewModel.onToggleVisualTransformationPassword() },
            onToggleVisualTransformationPasswordRepeat = { viewModel.onToggleVisualTransformationPasswordRepeat() },
            onNavigateToLoginScreen = onNavigateLoginScreen,
        )
    }
}

fun NavController.navigateToRegisterScreen() {
    navigate(AuthScreens.RegisterScreen)
}
