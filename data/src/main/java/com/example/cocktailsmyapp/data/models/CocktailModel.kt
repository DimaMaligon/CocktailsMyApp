package com.example.cocktailsmyapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CocktailModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var title: String,
    var image: String,
    var description: String,
    var recipe: String,
    var ingredients: List<String>
)