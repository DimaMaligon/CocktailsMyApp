package com.example.cocktailsmyapp.domain.interactors

import com.example.cocktailsmyapp.domain.models.Cocktail
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository

class DeleteCocktailUseCase constructor(
    private val repository: CocktailRepository
) {
    suspend fun deleteCocktail(cocktail: Cocktail) {
        repository.deleteCocktail(cocktail)
    }
}