package com.example.cocktailsmyapp.domain.models

data class Cocktail(
    val id: Long,
    val title: String,
    val image: String,
    val description: String,
    val recipe: String,
    val ingredients: List<String>
)