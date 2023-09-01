package com.example.cocktailsmyapp.data.models

import androidx.room.TypeConverter
import java.util.Arrays

import java.util.stream.Collectors




class CocktailsConverter {
    @TypeConverter
    fun fromCocktails(cocktails: List<String?>): String? {
        return cocktails.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toCocktails(data: String): List<String>? {
        return Arrays.asList(*data.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray())
    }
}