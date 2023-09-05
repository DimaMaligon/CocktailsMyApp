package com.example.cocktailsmyapp.domain.interactors

import com.example.cocktailsmyapp.domain.models.Cocktail
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository

class GetCocktailsUseCase constructor(
    private val repository: CocktailRepository
) {
    suspend fun getCocktails(): List<Cocktail> {
        return repository.getCocktails()
    }
}