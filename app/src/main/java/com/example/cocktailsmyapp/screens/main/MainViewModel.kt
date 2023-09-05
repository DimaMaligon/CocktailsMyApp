package com.example.cocktailsmyapp.screens.main

import androidx.lifecycle.ViewModel
import com.example.cocktailsmyapp.domain.interactors.GetCocktailsUseCase
import com.example.cocktailsmyapp.domain.models.Cocktail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getCocktailsUseCase: GetCocktailsUseCase) :
    ViewModel() {
    private val cocktailsListMutable: MutableStateFlow<MutableList<Cocktail>> =
        MutableStateFlow(mutableListOf())
    val cocktailsList: MutableStateFlow<MutableList<Cocktail>> = cocktailsListMutable

    suspend fun getCocktails() =
        withContext(Dispatchers.IO) {
            cocktailsListMutable.value = getCocktailsUseCase.getCocktails() as MutableList<Cocktail>
        }
}