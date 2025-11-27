package com.crs.receitafacil.ui.presentation.features.auth.register.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crs.receitafacil.core.sideeffects.SideEffect
import com.crs.receitafacil.core.utils.Constants
import com.crs.receitafacil.core.utils.extensions.observerState
import com.crs.receitafacil.core.utils.extensions.toFormattedPhoneNumber
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.model.RegisterInputValidationType
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.usecase.RegisterUserUseCase
import com.crs.receitafacil.ui.presentation.features.auth.register.domain.usecase.ValidateRegisterInputUseCase
import com.crs.receitafacil.ui.presentation.features.auth.register.presentation.state.RegisterUserState
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
class RegisterUserViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUserState())
    var uiState: StateFlow<RegisterUserState> = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    var sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onEvent(event: RegisterUserEvent) {
        when (event) {
            is RegisterUserEvent.OnRegisterClick -> onRegisterClick()
        }
    }

    fun onNameInputChange(newValue: String) {
        _uiState.update { it.copy(nameValue = newValue) }
        checkInputValidation()
    }

    fun onEmailInputChange(newValue: String) {
        _uiState.update { it.copy(emailValue = newValue) }
        checkInputValidation()
    }

    fun onPhoneInputChange(newValue: String) {
        _uiState.update { it.copy(phoneValue = newValue) }
        checkInputValidation()
    }

    fun onPasswordInputChange(newValue: String) {
        _uiState.update { it.copy(passwordValue = newValue) }
        checkInputValidation()
    }

    fun onPasswordRepeatInputChange(newValue: String) {
        _uiState.update { it.copy(passwordRepeatValue = newValue) }
        checkInputValidation()
    }

    fun onToggleVisualTransformationPassword() {
        _uiState.update { it.copy(isPasswordShow = !it.isPasswordShow) }
    }

    fun onToggleVisualTransformationPasswordRepeat() {
        _uiState.update { it.copy(isPasswordShowRepeatShow = !it.isPasswordShowRepeatShow) }
    }

    private fun onRegisterClick() {
        viewModelScope.launch {
            registerUserUseCase.invoke(
                parameters = RegisterUserUseCase.Parameters(
                    AddUserRequestModel(
                        name = _uiState.value.nameValue.trim(),
                        email = _uiState.value.emailValue.trim(),
                        phone = _uiState.value.phoneValue.toFormattedPhoneNumber(),
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
                            errorMessageRegister = error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccessFullyRegistered = response.isSuccessful
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message))
                }
            )
        }

    }

    private fun checkInputValidation() {
        val validationResult = validateRegisterInputUseCase(
            name = _uiState.value.nameValue,
            email = _uiState.value.emailValue,
            phone = _uiState.value.phoneValue,
            password = _uiState.value.passwordValue,
            passwordRepeated = _uiState.value.passwordRepeatValue
        )

        processInputValidationType(validationResult)

    }

    private fun processInputValidationType(validationResult: RegisterInputValidationType) {
        _uiState.update {
            when (validationResult) {
                RegisterInputValidationType.EmptyField -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.EMPTY_FIELD,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.NoEmail -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.INVALID_EMAIL,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordTooShort -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_TOO_SHORT,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordsDoNotMatch -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORDS_DO_NOT_MATCH,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordUpperCaseMissing -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_UPPERCASE_MISSING,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordSpecialCharMissing -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_SPECIAL_CHAR_MISSING,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordNumberMissing -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_NUMBER_MISSING,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PhoneNumberInvalid -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PHONE_NUMBER_INVALID,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.Valid -> {
                    it.copy(
                        errorMessageInput = null,
                        isInputValid = true
                    )
                }
            }

        }
    }

}