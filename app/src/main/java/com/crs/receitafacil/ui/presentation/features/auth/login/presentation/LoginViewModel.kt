package com.crs.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.receitafacil.core.sideeffects.SideEffect
import com.crs.receitafacil.core.utils.Constants
import com.crs.receitafacil.core.utils.extensions.observerState
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.model.LoginInputValidationType
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import com.crs.receitafacil.ui.presentation.features.auth.login.presentation.states.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    var uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    var sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                _uiState.update { it.copy(emailValue = event.email) }
                checkInputValidation()
            }

            is LoginEvent.OnPasswordChanged -> {
                _uiState.update { it.copy(passwordValue = event.password) }
                checkInputValidation()
            }

            is LoginEvent.OnLoginClick -> onLoginClick()

            is LoginEvent.OnToggleVisualTransformationPassword -> {
                _uiState.update { it.copy(isPasswordShown = !_uiState.value.isPasswordShown) }
            }
        }
    }

    private fun onLoginClick() {
        viewModelScope.launch {
            loginUseCase.invoke(
                parameters = LoginUseCase.Parameters(
                    AuthUserRequestModel(
                        email = _uiState.value.emailValue.trim(),
                        password = _uiState.value.passwordValue.trim()
                    )
                )
            ).observerState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageLoginProcess = error.message
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccessFullyLoggedIn = response.isSuccessFull
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message.toString()))
                    saveLocalStorageUserData(
                        token = response.token.toString(),
                        userName = response.userName.toString()
                    )
                }
            )
        }
    }

    private fun checkInputValidation() {
        val validationResult = validateLoginInputUseCase(
            email = _uiState.value.emailValue,
            password = _uiState.value.passwordValue
        )

        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType) {
        _uiState.update {
            when (type) {
                LoginInputValidationType.EmptyField -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.EMPTY_FIELD,
                        isInputValid = false
                    )
                }

                LoginInputValidationType.NoEmail -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.INVALID_EMAIL,
                        isInputValid = false
                    )
                }

                LoginInputValidationType.Valid -> {
                    it.copy(
                        errorMessageInput = null,
                        isInputValid = true
                    )
                }

            }
        }
    }

    private suspend fun saveLocalStorageUserData(token: String, userName: String) {
        saveUserDataUseCase.invoke(SaveUserDataUseCase.Parameters(token, userName)).observerState(
            onLoading = {/*NO-OP*/ },
            onFailure = {/*NO-OP*/ },
            onSuccess = {/*NO-OP*/ }
        )
    }
}