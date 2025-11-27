package com.crs.receitafacil.ui.presentation.features.auth.login.presentation.states

data class LoginUIState(
    val emailValue: String = "",
    val passwordValue: String = "",
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    val isPasswordShown: Boolean = false,
    val errorMessageInput: String? = null,
    val isSuccessFullyLoggedIn: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
