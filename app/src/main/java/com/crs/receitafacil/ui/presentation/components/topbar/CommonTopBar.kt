package com.crs.receitafacil.ui.presentation.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    title: String,
    enable: Boolean = true,
    actionImageVector: ImageVector? = null,
    navigationImageVector: ImageVector?,
    contentDescription: String? = null,
    onActionIconButton: () -> Unit = {},
    onNavigationIconButton: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        actions = {
            if (actionImageVector != null) {
                IconButton(
                    onClick = { onActionIconButton() },
                    enabled = enable
                ) {
                    Icon(
                        imageVector = actionImageVector,
                        contentDescription = contentDescription,
                        tint = if (enable) MaterialTheme.colorScheme.onSurfaceVariant else Color.Transparent
                    )
                }
            }
        },
        navigationIcon = {
            if (navigationImageVector != null) {
                IconButton(
                    onClick = { onNavigationIconButton() },
                    enabled = enable
                ) {
                    Icon(
                        imageVector = navigationImageVector,
                        contentDescription = contentDescription,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CommonTopBarPreview() {
    CommonTopBar(
        title = "Common Top Bar",
        enable = true,
        actionImageVector = Icons.Outlined.Save,
        navigationImageVector = Icons.AutoMirrored.Outlined.ArrowBack
    )
}