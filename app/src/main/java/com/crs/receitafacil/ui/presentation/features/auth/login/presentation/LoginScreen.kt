package com.crs.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.crs.receitafacil.core.sideeffects.SideEffect
import com.crs.receitafacil.core.utils.SingleEventEffect
import com.crs.receitafacil.core.utils.extensions.toast
import com.crs.receitafacil.ui.presentation.features.auth.login.presentation.components.LoginContent
import com.crs.receitafacil.ui.presentation.features.auth.login.presentation.states.LoginUIState
import com.crs.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginScreen(
    uiState: LoginUIState,
    sideEffectFlow: Flow<SideEffect>,
    onEvent: (LoginEvent) -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToHomeGraph: () -> Unit
) {

    val context = LocalContext.current

    SingleEventEffect(
        sideEffectFlow = sideEffectFlow
    ) { sideEffect ->
        when (sideEffect) {
            is SideEffect.ShowToast -> context.toast(sideEffect.message)
        }
    }

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isSuccessFullyLoggedIn
        },
        destination = {
            onNavigateToHomeGraph()
        }
    )

    Scaffold(
        content = { paddingValues ->
            LoginContent(
                paddingValues = paddingValues,
                uiState = uiState,
                onLoginClick = {
                    onEvent(LoginEvent.OnLoginClick)
                },
                onEmailChange = {
                    onEvent(LoginEvent.OnEmailChanged(it))
                },
                onPasswordChange = {
                    onEvent(LoginEvent.OnPasswordChanged(it))
                },
                onToggleVisualTransformationPassword = {
                    onEvent(LoginEvent.OnToggleVisualTransformationPassword)
                },
                onNavigateToRegisterScreen = onNavigateToRegisterScreen
            )
        }
    )
}