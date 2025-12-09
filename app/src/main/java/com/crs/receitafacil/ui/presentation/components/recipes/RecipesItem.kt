package com.crs.receitafacil.ui.presentation.components.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.crs.receitafacil.R
import com.crs.receitafacil.core.domain.model.RecipesResponseModel
import com.crs.receitafacil.ui.theme.LightChip
import com.crs.receitafacil.ui.theme.ReceitaFacilTheme
import com.crs.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun RecipesItem(
    modifier: Modifier = Modifier,
    recipe: RecipesResponseModel,
    onNavigateToRecipeDetailsScreen: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onNavigateToRecipeDetailsScreen(recipe.id)
            },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(CornerSize(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = recipe.name,
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "${recipe.preparationTime} ${stringResource(R.string.minutes_text)}",
                        fontFamily = poppinsFOntFamily,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        painter = painterResource(R.drawable.ic_ingredients),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "${recipe.totalIngredients} ${stringResource(R.string.ingredients_text)}",
                        fontFamily = poppinsFOntFamily,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
            recipe.ownerName?.let { ownerName ->
                ElevatedAssistChip(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = {/*NO-OP*/},
                    label = {
                        Text(text = ownerName)
                    },
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.size(AssistChipDefaults.IconSize),
                            imageVector = Icons.Outlined.SupervisedUserCircle,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    },
                    colors = ChipColors(
                        containerColor = LightChip,
                        labelColor = Color.Black,
                        disabledLabelColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledLeadingIconContentColor = Color.Transparent,
                        disabledTrailingIconContentColor = Color.Transparent,
                        trailingIconContentColor = Color.Transparent,
                        leadingIconContentColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipesItemPreview() {
    ReceitaFacilTheme{ 
        RecipesItem(
            recipe = RecipesResponseModel(
                id = "123",
                name = "Cachorro quente",
                ownerName = "Carlos",
                totalIngredients = 10,
                preparationTime = 20
            ),
            onNavigateToRecipeDetailsScreen = {}
        )
    }
}