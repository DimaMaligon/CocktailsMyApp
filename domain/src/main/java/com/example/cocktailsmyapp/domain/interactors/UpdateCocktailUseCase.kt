package com.example.cocktailsmyapp.domain.interactors

import com.example.cocktailsmyapp.domain.models.Cocktail
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository

class UpdateCocktailUseCase constructor(
    private val repository: CocktailRepository
) {
    suspend fun updateCocktail(cocktail: Cocktail) {
        repository.updateCocktail(cocktail)
    }
}