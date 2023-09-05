package com.example.cocktailsmyapp.data.di.model

import com.example.cocktailsmyapp.domain.interactors.AddCocktailUseCase
import com.example.cocktailsmyapp.domain.interactors.DeleteCocktailUseCase
import com.example.cocktailsmyapp.domain.interactors.GetCocktailsUseCase
import com.example.cocktailsmyapp.domain.interactors.UpdateCocktailUseCase
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesAddCocktail(repository: CocktailRepository): AddCocktailUseCase {
        return AddCocktailUseCase(repository)
    }

    @Provides
    fun providesDeleteCocktail(repository: CocktailRepository): DeleteCocktailUseCase {
        return DeleteCocktailUseCase(repository)
    }

    @Provides
    fun providesGetCocktails(repository: CocktailRepository): GetCocktailsUseCase {
        return GetCocktailsUseCase(repository)
    }

    @Provides
    fun providesUpdateCocktail(repository: CocktailRepository): UpdateCocktailUseCase {
        return UpdateCocktailUseCase(repository)
    }
}