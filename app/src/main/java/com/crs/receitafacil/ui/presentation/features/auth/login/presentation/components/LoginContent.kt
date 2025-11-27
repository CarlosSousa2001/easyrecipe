package com.crs.receitafacil.ui.presentation.features.auth.login.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.crs.receitafacil.ui.presentation.features.auth.login.presentation.states.LoginUIState
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme
import com.crs.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUIState,
    paddingValues: PaddingValues,
    onLoginClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        IconApp(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.login_text),
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = uiState.errorMessageLoginProcess ?: uiState.errorMessageInput.orEmpty(),
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                )
            }
            LoginContainer(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                isLoading = uiState.isLoading,
                emailValue = uiState.emailValue,
                passwordValue = uiState.passwordValue,
                buttonEnabled = uiState.isInputValid,
                isPasswordShown = uiState.isPasswordShown,
                onEmailChange = onEmailChange,
                onPasswordChange = onPasswordChange,
                onLoginButtonClick = onLoginClick,
                onToggleVisualTransformationPassword = onToggleVisualTransformationPassword
            )

            Row(
                modifier = Modifier
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.no_have_account_text),
                    fontFamily = poppinsFOntFamily,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clickable { onNavigateToRegisterScreen() },
                    text = stringResource(R.string.register_text),
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
private fun LoginContentPreview() {
    ReceitaFacilTheme() {
        LoginContent(
            paddingValues = PaddingValues(),
            uiState = LoginUIState(),
            onLoginClick = {},
            onEmailChange = {},
            onPasswordChange = {},
            onToggleVisualTransformationPassword = {},
            onNavigateToRegisterScreen = {}
        )
    }
}