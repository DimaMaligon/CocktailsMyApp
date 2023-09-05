package com.example.cocktailsmyapp.domain.interactors

import com.example.cocktailsmyapp.domain.models.Cocktail
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository

class AddCocktailUseCase constructor(
    private val repository: CocktailRepository
) {
    suspend fun addCocktail(cocktail: Cocktail) {
        return repository.addCocktail(cocktail)
    }
}