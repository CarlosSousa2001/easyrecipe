package com.crs.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryFilterChipList(
    modifier: Modifier = Modifier,
    categories: List<Int?>,
    selectedCategory: Int?,
    onSelectedCategory: (Int?) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(items = categories) { category ->
            CategoryFilterChip(
                category = category,
                isSelected = category == selectedCategory,
                onClick = { newIsSelected ->
                   if(newIsSelected) {
                       onSelectedCategory(category)
                   } else {
                       onSelectedCategory(null)
                   }
                }
            )
        }
    }
}