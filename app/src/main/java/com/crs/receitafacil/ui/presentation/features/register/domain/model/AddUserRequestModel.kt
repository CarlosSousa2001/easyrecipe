package com.crs.receitafacil.ui.presentation.features.register.domain.model

data class AddUserRequestModel(
    val name: String,
    val email: String,
    val password: String,
    val phone: String
)
