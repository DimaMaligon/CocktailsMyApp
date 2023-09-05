package com.example.cocktailsmyapp.data.repositories

import com.example.cocktailsmyapp.data.dao.CocktailsDao
import com.example.cocktailsmyapp.data.mappers.CocktailMapper
import com.example.cocktailsmyapp.domain.models.Cocktail
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val localSource: CocktailsDao,
    private val mapper: CocktailMapper
) : CocktailRepository
{
    override suspend fun getCocktails(): List<Cocktail> {
        return localSource.readCocktails()
            .map {
            mapper.mapToDomain(it)
        }
    }

    override suspend fun addCocktail(cocktail: Cocktail) {
        return localSource.insertToCocktails(mapper.mapToEntity(cocktail))
    }

    override suspend fun updateCocktail(cocktail: Cocktail) {
        return localSource.updateCocktail(mapper.mapToEntity(cocktail))
    }

    override suspend fun deleteCocktail(cocktail: Cocktail) {
        return localSource.deleteCocktail(mapper.mapToEntity(cocktail))
    }

}