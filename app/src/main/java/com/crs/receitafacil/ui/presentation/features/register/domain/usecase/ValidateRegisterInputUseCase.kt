package com.crs.receitafacil.ui.presentation.features.register.domain.usecase

import com.crs.receitafacil.core.utils.extensions.containsNumber
import com.crs.receitafacil.core.utils.extensions.containsSpecialChar
import com.crs.receitafacil.core.utils.extensions.containsUpperCase
import com.crs.receitafacil.ui.presentation.features.register.domain.model.RegisterInputValidationType

interface ValidateRegisterInputUseCase {
    operator fun invoke(
        name: String,
        email: String,
        phone: String,
        password: String,
        passwordRepeated: String
    ): RegisterInputValidationType
}

class ValidateRegisterInputUseCaseImpl : ValidateRegisterInputUseCase {

    override fun invoke(
        name: String,
        email: String,
        phone: String,
        password: String,
        passwordRepeated: String
    ): RegisterInputValidationType {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || passwordRepeated.isEmpty()) {
            return RegisterInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return RegisterInputValidationType.NoEmail
        }
        if (password != passwordRepeated) {
            return RegisterInputValidationType.PasswordsDoNotMatch
        }
        if (password.count() < 8) {
            return RegisterInputValidationType.PasswordTooShort
        }
        if (phone.count() < 11) {
            return RegisterInputValidationType.PhoneNumberInvalid
        }
        if (!password.containsNumber()) {
            return RegisterInputValidationType.PasswordNumberMissing
        }
        if (!password.containsUpperCase()) {
            return RegisterInputValidationType.PasswordUpperCaseMissing
        }
        if (!password.containsSpecialChar()) {
            return RegisterInputValidationType.PasswordSpecialCharMissing
        }
        return RegisterInputValidationType.Valid

    }
}
