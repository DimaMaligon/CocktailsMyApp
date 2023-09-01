package com.example.cocktailsmyapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cocktailsmyapp.data.dao.CocktailsDao
import com.example.cocktailsmyapp.data.models.CocktailModel
import com.example.cocktailsmyapp.data.models.CocktailsConverter

@Database(entities = [CocktailModel::class], version = 1)
@TypeConverters(CocktailsConverter::class)
abstract class DataBaseCocktails : RoomDatabase() {
    abstract fun cocktailsDao(): CocktailsDao
}