package com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.core.domain.model.RecipesResponseModel
import com.crs.receitafacil.ui.presentation.components.state.ErrorState
import com.crs.receitafacil.ui.presentation.components.state.LoadingIndicator
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme

@Composable
fun RecipeContent(
    modifier: Modifier = Modifier,
    isEmpty: Boolean,
    isLoading: Boolean,
    errorMessage: String?,
    selectedCategory: Int?,
    onSelectedCategory: (Int?) -> Unit,
    recipes: List<RecipesResponseModel>,
    onNavigateToRecipeDetailsScreen: (String) -> Unit
) {
    val categories = rememberCategories()

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            isLoading -> {
                LoadingIndicator()
            }

            errorMessage != null -> {
                ErrorState(message = errorMessage)
            }

            isEmpty -> {
                CategoryFilterChipList(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onSelectedCategory = onSelectedCategory
                )
                RecipesEmptyState(
                    modifier = Modifier.padding(12.dp)
                )
            }

            else -> {
                CategoryFilterChipList(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onSelectedCategory = onSelectedCategory
                )
                RecipesItemList(
                    recipes = recipes,
                    onNavigateToRecipesDetailsScreen = onNavigateToRecipeDetailsScreen
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeContentPreview() {
    ReceitaFacilTheme {
        RecipeContent(
            isEmpty = false,
            isLoading = false,
            errorMessage = null,
            selectedCategory = null,
            onSelectedCategory = {},
            recipes = listOf(
                RecipesResponseModel(
                    id = "123",
                    name = "Cachorro quente",
                    ownerName = "Carlos",
                    totalIngredients = 10,
                    preparationTime = 20
                )
            ),
            onNavigateToRecipeDetailsScreen = {}
        )
    }
}