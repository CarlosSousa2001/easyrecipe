package com.crs.receitafacil.core.domain.model

import androidx.annotation.DrawableRes
import com.crs.receitafacil.R

enum class CategoryEnum(
    val value: Int,
    val description: String,
    @DrawableRes val iconRes: Int
) {
    Breakfast(value = 0, description = "Café da Manhã", iconRes = R.drawable.ic_free_breakfast),
    Lunch(value = 1, description = "Almoço", iconRes = R.drawable.ic_restaurant),
    Dessert(value = 2, description = "Sobremesa", iconRes = R.drawable.ic_icecream),
    Snack(value = 3, description = "Lanche", iconRes = R.drawable.ic_fastfood),
    Dinner(value = 4, description = "Jantar", iconRes = R.drawable.ic_dinner_dining);

    companion object {
        fun fromInt(value: Int) = entries.firstOrNull { it.value == value }
        fun fromDescription(description: String) =
            entries.firstOrNull { it.description == description }

    }
}