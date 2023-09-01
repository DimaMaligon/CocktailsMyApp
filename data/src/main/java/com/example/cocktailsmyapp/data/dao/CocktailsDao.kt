package com.example.cocktailsmyapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.cocktailsmyapp.data.models.CocktailModel

@Dao
interface CocktailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToCocktails(word: CocktailModel)

    @Query("SELECT * FROM cocktailmodel")
    suspend fun readCocktails(): MutableList<CocktailModel>

    @Update
    suspend fun updateCocktail (cocktail: CocktailModel)

    @Delete
    suspend fun deleteCocktail (cocktail: CocktailModel)
}