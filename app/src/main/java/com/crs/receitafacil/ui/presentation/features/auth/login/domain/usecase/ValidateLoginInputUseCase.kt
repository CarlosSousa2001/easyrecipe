package com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase

import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.LoginInputValidationType

interface ValidateLoginInputUseCase {
    operator fun invoke(email: String, password: String): LoginInputValidationType
}

class ValidateLoginInputUseCaseImpl : ValidateLoginInputUseCase {
    override fun invoke(
        email: String,
        password: String
    ): LoginInputValidationType {
        if (email.isEmpty() || password.isEmpty()) {
            return LoginInputValidationType.EmptyField
        }
        if ("@" !in email) {
            return LoginInputValidationType.NoEmail
        }
        return LoginInputValidationType.Valid
    }

}
