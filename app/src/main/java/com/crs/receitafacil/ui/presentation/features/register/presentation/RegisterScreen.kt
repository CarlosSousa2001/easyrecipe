package com.crs.receitafacil.ui.presentation.features.register.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.crs.receitafacil.ui.presentation.features.register.presentation.components.RegisterContent
import com.crs.receitafacil.ui.presentation.features.register.presentation.state.RegisterUserState
import com.crs.receitafacil.ui.presentation.navigation.NavDestinationHelper

@Composable
fun RegisterScreen(
    uiState: RegisterUserState,
    onEvent: (RegisterUserEvent) -> Unit,
    onNavigateToLoginScreen: () -> Unit,

    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onToggleVisualTransformationPasswordRepeat: () -> Unit,
) {

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isSuccessFullyRegistered
        },
        destination = {
            onNavigateToLoginScreen()
        }
    )

    Scaffold(
        content = { padding ->
            RegisterContent(
                paddingValues = padding,
                uiState = uiState,
                onRegisterClick = { onEvent(RegisterUserEvent.OnRegisterClick) },
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordRepeatedChanged = onPasswordRepeatedChanged,
                onToggleVisualTransformationPassword = onToggleVisualTransformationPassword,
                onToggleVisualTransformationPasswordRepeat = onToggleVisualTransformationPasswordRepeat,
                onNavigateToLoginScreen = onNavigateToLoginScreen
            )
        },

        )
}