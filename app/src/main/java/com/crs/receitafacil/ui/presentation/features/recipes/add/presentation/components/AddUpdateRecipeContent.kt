package com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.EmojiFoodBeverage
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.R
import com.crs.receitafacil.core.domain.model.IngredientsModel
import com.crs.receitafacil.ui.presentation.components.state.ErrorState
import com.crs.receitafacil.ui.presentation.components.state.LoadingIndicator
import com.crs.receitafacil.ui.presentation.components.textfield.TextEntryModule
import com.crs.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun AddUpdateRecipeContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    nameValue: String,
    isLoading: Boolean,
    errorMessageInput: String?,
    errorMessageRegisterProcess: String?,
    ingredients: List<IngredientsModel>,
    categoryValue: String,
    preparationTimeValue: String,
    preparationModeValue: String,
    onNameValueChange: (String) -> Unit,
    onCategoryValueChange: (String) -> Unit,
    onPreparationTimeValueChange: (String) -> Unit,
    onPreparationModeValueChange: (String) -> Unit,
    onOpenDialog: () -> Unit,
    onRemoveIngredient: (IngredientsModel) -> Unit,
) {

    when {
        isLoading -> {
            LoadingIndicator()
        }

        errorMessageRegisterProcess != null -> {
            ErrorState(message = errorMessageRegisterProcess)
        }

        else -> {
            Column(
                modifier = modifier.fillMaxWidth().padding(paddingValues = paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(!errorMessageInput.isNullOrEmpty()){
                    Text(
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                        text = errorMessageInput ?: "",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFOntFamily,
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextEntryModule(
                        description = stringResource(R.string.description_name_recipe_text),
                        hint = stringResource(id = R.string.hint_name_recipe_text),
                        leadingIcon = Icons.Outlined.EmojiFoodBeverage,
                        textValue = nameValue,
                        onValueChanged = onNameValueChange,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    TextEntryModule(
                        description = stringResource(R.string.description_time_preparation_text),
                        hint = stringResource(id = R.string.hint_time_preparation_text),
                        leadingIcon = Icons.Outlined.Timer,
                        textValue = preparationTimeValue,
                        onValueChanged = onPreparationTimeValueChange,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        keyboardType = KeyboardType.Number
                    )
                    CategoryDropdownMenu(
                        selectedText = categoryValue,
                        categories = stringArrayResource(id = R.array.categories),
                        onValueChange = onCategoryValueChange
                    )

                    TextEntryModule(
                        modifier = Modifier.heightIn(min = 200.dp),
                        description = stringResource(R.string.description_mode_preparation_text),
                        hint = stringResource(id = R.string.hint_mode_preparation_text),
                        leadingIcon = Icons.Outlined.Description,
                        textValue = preparationModeValue,
                        onValueChanged = onPreparationModeValueChange,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    IngredientItemList(
                        ingredients = ingredients.toMutableList(),
                        onOpenDialog = onOpenDialog,
                        onRemoveIngredient = onRemoveIngredient
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun AddUpdateRecipeContentPreview() {
    AddUpdateRecipeContent(
        paddingValues = PaddingValues(8.dp),
        nameValue = "Bolo de Chocolate",
        isLoading = false,
        errorMessageInput = null,
        errorMessageRegisterProcess = null,
        ingredients = mutableListOf(
            IngredientsModel("1", "Farinha", "2 xícaras"),
            IngredientsModel("2", "Ovos", "3 unidades"),
            IngredientsModel("3", "Açúcar", "1 xícara")
        ),
        categoryValue = "Sobremesa",
        preparationTimeValue = "45 minutos",
        preparationModeValue = "Misture os ingredientes e asse por 40 minutos a 180ºC.",
        onNameValueChange = {},
        onCategoryValueChange = {},
        onPreparationTimeValueChange = {},
        onPreparationModeValueChange = {},
        onOpenDialog = {},
        onRemoveIngredient = {}
    )
}