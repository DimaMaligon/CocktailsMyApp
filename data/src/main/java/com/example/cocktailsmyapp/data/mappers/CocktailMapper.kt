package com.example.cocktailsmyapp.data.mappers

import com.example.cocktailsmyapp.data.models.CocktailModel
import com.example.cocktailsmyapp.domain.models.Cocktail

class CocktailMapper {
    fun mapToEntity(cocktail: Cocktail): CocktailModel = with(cocktail) {
        CocktailModel(
            id = id,
            title = title,
            image = image,
            description = description,
            recipe = recipe,
            ingredients = ingredients
        )
    }

    fun mapToDomain(cocktailEntity: CocktailModel): Cocktail = with(cocktailEntity) {
        Cocktail(
            id = id,
            title = title,
            image = image,
            description = description,
            recipe = recipe,
            ingredients = ingredients
        )
    }
}