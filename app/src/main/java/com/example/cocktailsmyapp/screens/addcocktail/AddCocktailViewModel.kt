package com.example.cocktailsmyapp.screens.addcocktail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailsmyapp.R
import com.example.cocktailsmyapp.domain.interactors.AddCocktailUseCase
import com.example.cocktailsmyapp.domain.models.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddCocktailViewModel @Inject constructor(private val addCocktailUseCase: AddCocktailUseCase) :
    ViewModel() {
    private val imageUri = R.string.path_main_domain.toString() + R.drawable.cocktail1

    private val imageCocktailMutable = MutableStateFlow(imageUri)
    private val titleCocktailMutable = MutableStateFlow(R.string.new_cocktail.toString())
    private val descriptionCocktailMutable = MutableStateFlow(R.string.new_description.toString())
    private val recipeCocktailMutable = MutableStateFlow(R.string.new_recipe.toString())
    private val ingredientsListMutable: MutableStateFlow<MutableList<String>> =
        MutableStateFlow(mutableListOf())
    val ingredientsList: MutableStateFlow<MutableList<String>> = ingredientsListMutable

    fun addCocktail() {
        viewModelScope.launch {
            addCocktailUseCase.addCocktail(
                Cocktail(
                    0,
                    title = titleCocktailMutable.value,
                    image = imageCocktailMutable.value,
                    description = descriptionCocktailMutable.value,
                    recipe = recipeCocktailMutable.value,
                    ingredients = ingredientsListMutable.value
                )
            )
        }
    }

    fun setTitle(title: String) {
        titleCocktailMutable.value = title
    }

    fun setImage(image: String) {
        imageCocktailMutable.value = image
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
}