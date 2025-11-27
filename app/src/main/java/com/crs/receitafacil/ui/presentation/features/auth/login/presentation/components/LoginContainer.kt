package com.crs.receitafacil.ui.presentation.features.auth.login.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.R
import com.crs.receitafacil.ui.presentation.components.button.AuthButton
import com.crs.receitafacil.ui.presentation.components.textfield.TextEntryModule
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    emailValue: String,
    passwordValue: String,
    buttonEnabled: Boolean,
    isPasswordShown: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextEntryModule(
            description = stringResource(R.string.description_email_text),
            hint = stringResource(R.string.hint_email_text),
            leadingIcon = Icons.Default.Email,
            imeAction = ImeAction.Next,
            textValue = emailValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            onValueChanged = onEmailChange,
            trailingIcon = null,
            onTrailingIconClick = null,
        )

        TextEntryModule(
            description = stringResource(R.string.description_password_text),
            hint = stringResource(R.string.hint_password_text),
            leadingIcon = Icons.Default.VpnKey,
            imeAction = ImeAction.Next,
            textValue = passwordValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            onValueChanged = onPasswordChange,
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = {
                onToggleVisualTransformationPassword()
            },
            visualTransformation = if (isPasswordShown) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardType = KeyboardType.Password
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            AuthButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(10.dp)),
                text = stringResource(R.string.login_text),
                contentColor = Color.White,
                onButtonClick = onLoginButtonClick,
                enabled = buttonEnabled,
                isLoading = isLoading
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContainerPreview() {
    ReceitaFacilTheme {
        LoginContainer(
            emailValue = "",
            passwordValue = "",
            buttonEnabled = false,
            isPasswordShown = false,
            onEmailChange = {},
            onPasswordChange = {},
            onLoginButtonClick = {},
            isLoading = false,
            onToggleVisualTransformationPassword = {},
        )
    }
}