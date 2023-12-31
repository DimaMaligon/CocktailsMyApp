package com.example.cocktailsmyapp.screens.cardcocktail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailsmyapp.R
import com.example.cocktailsmyapp.domain.interactors.DeleteCocktailUseCase
import com.example.cocktailsmyapp.domain.interactors.UpdateCocktailUseCase
import com.example.cocktailsmyapp.domain.models.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardCocktailViewModel @Inject constructor(private val updateCocktailUseCase: UpdateCocktailUseCase, private val deleteCocktailUseCase: DeleteCocktailUseCase) :
    ViewModel() {
    val imageUri = (R.string.path_main_domain + R.drawable.cocktail1).toString()

    private val imageCocktailMutable = MutableStateFlow(imageUri)
    private val titleCocktailMutable = MutableStateFlow(R.string.new_cocktail.toString())
    private val descriptionCocktailMutable = MutableStateFlow(R.string.new_description.toString())
    private val recipeCocktailMutable = MutableStateFlow(R.string.new_recipe.toString())
    private val ingredientsListMutable: MutableStateFlow<MutableList<String>> =
        MutableStateFlow(mutableListOf())
    val ingredientsList: MutableStateFlow<MutableList<String>> = ingredientsListMutable
    private val cocktailObjectMutable = MutableStateFlow(
        Cocktail(0, "", "", "", "", listOf(""))
    )
    val cocktailObject: StateFlow<Cocktail> = cocktailObjectMutable

    fun updateCocktail() {
        viewModelScope.launch {
            updateCocktailUseCase.updateCocktail(
                Cocktail(
                    cocktailObjectMutable.value.id,
                    title = titleCocktailMutable.value,
                    image = imageCocktailMutable.value,
                    description = descriptionCocktailMutable.value,
                    recipe = recipeCocktailMutable.value,
                    ingredients = ingredientsListMutable.value
                )
            )
        }
    }

    fun deleteCocktail() {
        viewModelScope.launch {
            deleteCocktailUseCase.deleteCocktail(
                cocktailObjectMutable.value
            )
        }
    }

    fun setTitle(title: String) {
        titleCocktailMutable.value = title
    }

    fun setDescription(description: String) {
        descriptionCocktailMutable.value = description
    }

    fun setRecipe(recipe: String) {
        recipeCocktailMutable.value = recipe
    }

    fun setIngredientsList(list: MutableList<String>) {
        ingredientsListMutable.value = list
    }

    fun setCocktail(cocktail: Cocktail) {
        cocktailObjectMutable.value = cocktail
    }
}