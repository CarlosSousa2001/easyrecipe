package com.crs.receitafacil.ui.presentation.features.auth.register.domain.model

enum class RegisterInputValidationType {
    EmptyField,
    NoEmail,
    PasswordsDoNotMatch,
    PasswordUpperCaseMissing,
    PasswordSpecialCharMissing,
    PasswordTooShort,
    PhoneNumberInvalid,
    Valid,
    PasswordNumberMissing
}