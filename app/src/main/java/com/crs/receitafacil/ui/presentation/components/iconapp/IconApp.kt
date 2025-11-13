package com.crs.receitafacil.ui.presentation.components.iconapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.R

@Composable
fun IconApp(
    modifier: Modifier = Modifier
) {

    val icon = if(isSystemInDarkTheme()) {
        painterResource(R.drawable.ic_recipe_light)
    } else {
        painterResource(R.drawable.ic_recipe_dark)
    }

    Image(
        modifier = modifier.size(80.dp),
        painter = icon,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,

    )
}