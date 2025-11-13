package com.crs.receitafacil.core.sideeffects

sealed interface SideEffect {

    data class ShowToast(val message: String) : SideEffect

}