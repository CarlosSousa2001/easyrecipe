package com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.R
import com.crs.receitafacil.core.domain.model.IngredientsModel
import com.crs.receitafacil.ui.presentation.components.ingretdients.IngredientItem
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme
import com.crs.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun IngredientItemList(
    modifier: Modifier = Modifier,
    ingredients: MutableList<IngredientsModel>,
    onOpenDialog: () -> Unit,
    onRemoveIngredient: (IngredientsModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        content = {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(R.string.ingredients_text),
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFOntFamily,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    IconButton(
                        onClick = onOpenDialog
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddBox,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            items(
                items = ingredients,
                key = { item -> item.id },
                itemContent = { ingredient ->
                    IngredientItem(
                        productName = ingredient.name,
                        productQuantity = ingredient.quantity,
                        iconButton = true,
                        onRemoveIngredient = {
                            onRemoveIngredient(ingredient)
                        }
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun IngredientItemListPreview() {
    ReceitaFacilTheme {
        IngredientItemList(
            ingredients = mutableListOf(),
            onOpenDialog = {},
            onRemoveIngredient = {}
        )
    }
}
