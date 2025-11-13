package com.crs.receitafacil.ui.presentation.features.register.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
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
import com.crs.receitafacil.ui.presentation.components.transformations.PhoneOriginalTransformation
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme

@Composable
fun RegisterContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    nameValue: String,
    emailValue: String,
    phoneValue: String,
    passwordValue: String,
    passwordRepeatedValue: String,
    isPasswordShown: Boolean,
    buttonEnabled: Boolean,
    isPasswordRepeatedShown: Boolean,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onToggleVisualTransformationPasswordRepeat: () -> Unit,

    ) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_nome_text),
            hint = stringResource(R.string.hint_nome_text),
            leadingIcon = Icons.Default.Person,
            textValue = nameValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = onNameChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )
        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_email_text),
            hint = stringResource(R.string.hint_email_text),
            leadingIcon = Icons.Default.Email,
            textValue = emailValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = onEmailChanged,
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_phone_text),
            hint = stringResource(R.string.hint_phone_text),
            leadingIcon = Icons.Default.PhoneAndroid,
            textValue = phoneValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = {
                if (it.length <= 11) {
                    onPhoneChanged(it)
                }
            },
            keyboardType = KeyboardType.Phone,
            visualTransformation = PhoneOriginalTransformation(),
            trailingIcon = null,
            onTrailingIconClick = null
        )

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_password_text),
            hint = stringResource(R.string.hint_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = onPasswordChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (isPasswordShown) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onToggleVisualTransformationPassword
        )

        TextEntryModule(
            modifier = Modifier.fillMaxWidth(),
            description = stringResource(R.string.description_repeat_password_text),
            hint = stringResource(R.string.hint_repeat_password_text),
            leadingIcon = Icons.Default.VpnKey,
            textValue = passwordRepeatedValue,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant,
            cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
            imeAction = ImeAction.Next,
            onValueChanged = onPasswordRepeatedChanged,
            keyboardType = KeyboardType.Password,
            visualTransformation = if (isPasswordRepeatedShown) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = Icons.Default.RemoveRedEye,
            onTrailingIconClick = onToggleVisualTransformationPasswordRepeat
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            AuthButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .shadow(5.dp, RoundedCornerShape(25.dp)),
                text = stringResource(R.string.register_button_text),
                contentColor = Color.White,
                onButtonClick = onButtonClick,
                enabled = buttonEnabled,
                isLoading = isLoading
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun RegisterContainerPreview() {
    ReceitaFacilTheme {
        RegisterContainer(
            nameValue = "",
            emailValue = "",
            phoneValue = "",
            passwordValue = "",
            passwordRepeatedValue = "",
            buttonEnabled = true,
            onNameChanged = {},
            onEmailChanged = {},
            onPhoneChanged = {},
            onPasswordChanged = {},
            onPasswordRepeatedChanged = {},
            onButtonClick = { /*TODO*/ },
            isPasswordShown = false,
            isPasswordRepeatedShown = false,
            onToggleVisualTransformationPassword = { /*TODO*/ },
            onToggleVisualTransformationPasswordRepeat = { /*TODO*/ },
            isLoading = false
        )
    }
}