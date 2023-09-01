package com.example.cocktailsmyapp.data.di.model

import com.example.cocktailsmyapp.domain.interactors.CocktailInteractor
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesCocktailInteractor(repository: CocktailRepository): CocktailInteractor {
        return CocktailInteractor(repository)
    }
}