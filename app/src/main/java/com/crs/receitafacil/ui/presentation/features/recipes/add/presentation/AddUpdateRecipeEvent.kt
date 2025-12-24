package com.crs.receitafacil.ui.presentation.features.recipes.add.presentation

import com.crs.receitafacil.core.domain.model.IngredientsModel

sealed class AddUpdateRecipeEvent {
    data class OnNameInputChange(val name: String) : AddUpdateRecipeEvent()
    data class OnCategoryInputChange(val category: String) : AddUpdateRecipeEvent()
    data class OnPreparationTimeInputChange(val preparationTime: String) : AddUpdateRecipeEvent()
    data class OnPreparationModeInputChange(val preparationMode: String) : AddUpdateRecipeEvent()
    //data class OnIngredientInputChange(val ingredient: String) : AddUpdateRecipeEvent()
    data class OnIngredientProductNameInputChange(val ingredientProductName: String) : AddUpdateRecipeEvent()
    data class OnIngredientProductQuantityInputChange(val ingredientProductQuantity: String) : AddUpdateRecipeEvent()
    data class OnRemoveIngredient(val ingredient: IngredientsModel) : AddUpdateRecipeEvent()
    data object OnAddIngredient : AddUpdateRecipeEvent()
    data object OnAddOrUpdateRecipe : AddUpdateRecipeEvent()
}
