package com.crs.receitafacil.ui.presentation.features.auth.login.presentation

sealed class LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent()
    data class OnPasswordChanged(val password: String) : LoginEvent()
    data object OnToggleVisualTransformationPassword : LoginEvent()
    data object OnLoginClick : LoginEvent()
}