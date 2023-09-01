package com.example.cocktailsmyapp.data

import android.app.Application
import androidx.room.Room
import com.example.cocktailsmyapp.data.dao.CocktailsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbManager @Inject constructor(val application: Application) {
    lateinit var db: DataBaseCocktails

    fun openDb() {
        db = Room.databaseBuilder(
            application,
            DataBaseCocktails::class.java,
            "words.db"
        ).build()
    }

    fun getDao(): CocktailsDao {
        return db.cocktailsDao()
    }
}