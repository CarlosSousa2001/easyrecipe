package com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.core.domain.model.RecipesResponseModel
import com.crs.receitafacil.ui.presentation.components.recipes.RecipesItem
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme

@Composable
fun RecipesItemList(
    modifier: Modifier = Modifier,
    recipes: List<RecipesResponseModel>,
    onNavigateToRecipesDetailsScreen: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp),
        content = {
            items(
                items = recipes,
                key = { item ->
                    item.id
                }
            ) { recipe ->
                RecipesItem(
                    recipe = recipe,
                    onNavigateToRecipeDetailsScreen = onNavigateToRecipesDetailsScreen
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun RecipesItemListPreview() {
    ReceitaFacilTheme {
        RecipesItemList(
            recipes = listOf(
                RecipesResponseModel(
                    id = "123",
                    name = "Cachorro quente",
                    ownerName = "Carlos",
                    totalIngredients = 10,
                    preparationTime = 20
                )
            ),
            onNavigateToRecipesDetailsScreen = {}
        )
    }
}