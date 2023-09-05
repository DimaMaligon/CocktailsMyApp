package com.example.cocktailsmyapp.domain.repositories

import com.example.cocktailsmyapp.domain.models.Cocktail

interface CocktailRepository {
    suspend fun getCocktails(): List<Cocktail>

    suspend fun addCocktail(cocktail: Cocktail)

    suspend fun updateCocktail(cocktail: Cocktail)

    suspend fun deleteCocktail(cocktail: Cocktail)
}