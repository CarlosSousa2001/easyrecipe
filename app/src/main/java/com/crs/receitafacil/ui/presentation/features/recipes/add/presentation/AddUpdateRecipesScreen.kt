package com.crs.receitafacil.ui.presentation.features.recipes.add.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.crs.receitafacil.R
import com.crs.receitafacil.core.domain.model.IngredientsModel
import com.crs.receitafacil.core.sideeffects.SideEffect
import com.crs.receitafacil.core.utils.SingleEventEffect
import com.crs.receitafacil.core.utils.extensions.toast
import com.crs.receitafacil.ui.presentation.components.topbar.CommonTopBar
import com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.components.AddIngredientDialog
import com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.components.AddUpdateRecipeContent
import com.crs.receitafacil.ui.presentation.features.recipes.add.presentation.state.AddUpdateRecipeUIState
import com.crs.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun AddUpdateRecipesScreen(
    uiState: AddUpdateRecipeUIState,
    buttonEnabled: Boolean,
    sideEffectFlow: Flow<SideEffect>,
    addIngredientDialogShow: Boolean,
    ingredients: MutableList<IngredientsModel>,
    onDismiss: () -> Unit,
    onOpenDialog: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToRecipesScreen: () -> Unit,
    onEvent: (AddUpdateRecipeEvent) -> Unit = {}
) {
    val context = LocalContext.current

    SingleEventEffect(sideEffectFlow = sideEffectFlow) { sideEffect ->
        when (sideEffect) {
            is SideEffect.ShowToast -> context.toast(sideEffect.message)
        }
    }

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isOperationSuccessFull
        },
        destination = {
            onNavigateToRecipesScreen()
        }
    )

    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                title = if (uiState.currentRecipeId.isEmpty()) stringResource(R.string.new_recipe_text) else
                    stringResource(R.string.update_recipe_text),
                enable = buttonEnabled,
                actionImageVector = Icons.Outlined.Save,
                navigationImageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                onActionIconButton = { onEvent(AddUpdateRecipeEvent.OnAddOrUpdateRecipe) },
                onNavigationIconButton = { onNavigateUp }
            )
        },
        content = { paddingValues ->
            AddUpdateRecipeContent(
                paddingValues = paddingValues,
                nameValue = uiState.nameInput,
                isLoading = uiState.isLoading,
                errorMessageInput = uiState.errorMessageInput,
                errorMessageRegisterProcess = uiState.errorMessageRegisterProcess,
                ingredients = ingredients,
                categoryValue = uiState.categoryInput,
                preparationTimeValue = uiState.preparationTimeInput,
                preparationModeValue = uiState.preparationModeInput,
                onNameValueChange = { onEvent(AddUpdateRecipeEvent.OnNameInputChange(it)) },
                onCategoryValueChange = { onEvent(AddUpdateRecipeEvent.OnCategoryInputChange(it)) },
                onPreparationTimeValueChange = { onEvent(AddUpdateRecipeEvent.OnPreparationTimeInputChange(it)) },
                onPreparationModeValueChange = { onEvent(AddUpdateRecipeEvent.OnPreparationModeInputChange(it)) },
                onOpenDialog = onOpenDialog,
                onRemoveIngredient = { onEvent(AddUpdateRecipeEvent.OnRemoveIngredient(it)) },
            )
        }

    )

    if(addIngredientDialogShow) {
        AddIngredientDialog(
            onDismiss = onDismiss,
            onShowConfirm = { onEvent(AddUpdateRecipeEvent.OnAddIngredient) },
            onIngredientProductNameInputChange = { onEvent(AddUpdateRecipeEvent.OnIngredientProductNameInputChange(it)) },
            onIngredientProductQuantityInputChange = { onEvent(AddUpdateRecipeEvent.OnIngredientProductQuantityInputChange(it)) },
            productNameValue =  uiState.ingredientProductNameInput,
            productQuantityValue = uiState.ingredientProductQuantityInput,
            errorMessageInput = uiState.errorMessageDialogInput
        )
    }

}