package com.crs.receitafacil.ui.presentation.components.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.crs.receitafacil.R

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        actions = {
            IconButton(
                onClick = onNavigationToUsersConnectionScreen
            ) {
                Icon(
                    imageVector = Icons.Outlined.PeopleOutline,
                    contentDescription = "User connections",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onNavigationToSearchScreen
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search Screen",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onNavigationToProfileScreen
            ) {
                Icon(
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = "Profile Screen",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNavigationToAddRecipeScreen,
                containerColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        tint = Color.White,
                        contentDescription = "Add Recipe"
                    )
                },
                text = { Text(text = stringResource(id = R.string.new_recipe_text), color = Color.White) }
            )
        }
    )
}

@Preview
@Composable
private fun BottomBarPreview() {
    BottomBar(
        onNavigationToProfileScreen = {},
        onNavigationToSearchScreen = {},
        onNavigationToAddRecipeScreen = {},
        onNavigationToUsersConnectionScreen = {}
    )
}