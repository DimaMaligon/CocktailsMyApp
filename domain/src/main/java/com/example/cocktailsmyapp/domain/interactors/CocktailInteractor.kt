package com.example.cocktailsmyapp.domain.interactors

import com.example.cocktailsmyapp.domain.models.Cocktail
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository

class CocktailInteractor constructor(
    private val repository: CocktailRepository
) {

    suspend fun getCocktails(): List<Cocktail> {
        return repository.getCocktails()
    }

    suspend fun getCocktailById(id: Long): Cocktail? {
        return repository.getCocktailById(id)
    }

    suspend fun addCocktail(cocktail: Cocktail) {
        return repository.addCocktail(cocktail)
    }

    suspend fun updateCocktail(cocktail: Cocktail) {
        repository.updateCocktail(cocktail)
    }

    suspend fun deleteCocktail(cocktail: Cocktail) {
        repository.deleteCocktail(cocktail)
    }

}