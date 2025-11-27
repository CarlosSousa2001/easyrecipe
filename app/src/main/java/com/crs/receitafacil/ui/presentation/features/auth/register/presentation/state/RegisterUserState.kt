package com.crs.receitafacil.ui.presentation.features.auth.register.presentation.state

data class RegisterUserState(
    val nameValue: String = "",
    val emailValue: String = "",
    val phoneValue: String = "",
    val passwordValue: String = "",
    val passwordRepeatValue: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val isPasswordShowRepeatShow: Boolean = false,
    val errorMessageInput: String? = "",
    val isLoading: Boolean = false,
    val isSuccessFullyRegistered: Boolean = false,
    val errorMessageRegister: String? = null
)
