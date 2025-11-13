package com.crs.receitafacil.ui.presentation.features.register.presentation

sealed class RegisterUserEvent {
    data object OnRegisterClick : RegisterUserEvent()
}