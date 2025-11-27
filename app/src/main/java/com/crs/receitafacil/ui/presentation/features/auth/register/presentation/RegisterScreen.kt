package com.crs.receitafacil.ui.presentation.features.auth.register.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.crs.receitafacil.core.sideeffects.SideEffect
import com.crs.receitafacil.core.utils.SingleEventEffect
import com.crs.receitafacil.core.utils.extensions.toast
import com.crs.receitafacil.ui.presentation.features.auth.register.presentation.components.RegisterContent
import com.crs.receitafacil.ui.presentation.features.auth.register.presentation.state.RegisterUserState
import com.crs.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun RegisterScreen(
    uiState: RegisterUserState,
    sideEffectFlow: Flow<SideEffect>,
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

    val context = LocalContext.current

    SingleEventEffect(sideEffectFlow) { sideEffect ->
        when(sideEffect){
            is SideEffect.ShowToast -> context.toast(sideEffect.message)
        }
    }

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