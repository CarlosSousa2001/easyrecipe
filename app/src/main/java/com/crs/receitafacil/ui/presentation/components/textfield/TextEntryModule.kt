package com.crs.receitafacil.ui.presentation.components.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme
import com.crs.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun TextEntryModule(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp, end = 8.dp, bottom = 5.dp),
    description: String,
    hint: String,
    leadingIcon: ImageVector,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Ascii,
    textValue: String,
    textColor: Color,
    cursorColor: Color,
    onValueChanged: (String) -> Unit,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = {},
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = textValue,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = description,
                    color = textColor,
                    fontFamily = poppinsFOntFamily,
                )
            },
            textStyle = TextStyle(
                fontFamily = poppinsFOntFamily,
                textAlign = TextAlign.Start
            ),
            placeholder = {
                Text(
                    text = hint,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = poppinsFOntFamily,
                )
            },

            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = "Icone do text field",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = "Icone do text field",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.clickable {
                            if(onTrailingIconClick != null) {
                                onTrailingIconClick()
                            }
                        }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = keyboardType,
                autoCorrect = true,
                imeAction = imeAction
            ),

            visualTransformation = visualTransformation,
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = cursorColor,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextEntryModulePreview() {
    ReceitaFacilTheme {
        TextEntryModule(
            description = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 5.dp),
            hint = "teste@gmail.com",
            leadingIcon = Icons.Default.Email,
            textValue = "123456",
            textColor = Color.Black,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            onValueChanged = {},
            trailingIcon = Icons.Filled.RemoveRedEye,
            onTrailingIconClick = {},
            visualTransformation = PasswordVisualTransformation()
        )
    }
}