package com.crs.receitafacil.ui.presentation.features.register.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crs.receitafacil.R
import com.crs.receitafacil.ui.presentation.components.iconapp.IconApp
import com.crs.receitafacil.ui.presentation.features.register.presentation.state.RegisterUserState
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme
import com.crs.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    uiState: RegisterUserState,
    paddingValues: PaddingValues,
    onRegisterClick: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,

    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onToggleVisualTransformationPasswordRepeat: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
//            .padding(horizontal = 8.dp)
    ) {
        IconApp(
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.TopCenter),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.register_text),
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    text = uiState.errorMessageInput ?: uiState.errorMessageInput.orEmpty(),
                    fontFamily = poppinsFOntFamily,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.error
                )
            }

            RegisterContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 8.dp),
                isLoading = uiState.isLoading,
                nameValue = uiState.nameValue,
                emailValue = uiState.emailValue,
                phoneValue = uiState.phoneValue,
                passwordValue = uiState.passwordValue,
                passwordRepeatedValue = uiState.passwordRepeatValue,
                buttonEnabled = uiState.isInputValid,
                isPasswordShown = uiState.isPasswordShow,
                isPasswordRepeatedShown = uiState.isPasswordShowRepeatShow,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordRepeatedChanged = onPasswordRepeatedChanged,
                onButtonClick = onRegisterClick,
                onToggleVisualTransformationPassword = onToggleVisualTransformationPassword,
                onToggleVisualTransformationPasswordRepeat = onToggleVisualTransformationPasswordRepeat,
            )

            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    fontFamily = poppinsFOntFamily,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { onNavigateToLoginScreen() },
                    text = stringResource(R.string.login_text),
                    fontFamily = poppinsFOntFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContentPreview() {
    ReceitaFacilTheme {
        RegisterContent(
            uiState = RegisterUserState(),
            paddingValues = PaddingValues(16.dp),
            onRegisterClick = { },
            onNameChanged = { },
            onEmailChanged = { },
            onPhoneChanged = { },
            onPasswordChanged = { },
            onPasswordRepeatedChanged = { },
            onToggleVisualTransformationPassword = { },
            onToggleVisualTransformationPasswordRepeat = { },
            onNavigateToLoginScreen = { }
        )
    }
}