package com.crs.receitafacil.ui.presentation.features.auth.login.domain.model

data class TokenResponseModel(
    val isSuccessFull : Boolean,
    val message: String? = null,
    val token : String? = null,
    val userName : String? = null
)
