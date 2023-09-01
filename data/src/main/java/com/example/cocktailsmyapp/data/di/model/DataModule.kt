package com.example.cocktailsmyapp.data.di.model

import android.app.Application
import com.example.cocktailsmyapp.data.DbManager
import com.example.cocktailsmyapp.data.dao.CocktailsDao
import com.example.cocktailsmyapp.data.mappers.CocktailMapper
import com.example.cocktailsmyapp.data.repositories.CocktailRepositoryImpl
import com.example.cocktailsmyapp.domain.repositories.CocktailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class DataModule {
    @Module
    @InstallIn(SingletonComponent::class)
    class AppModule {

        @Provides
        @Singleton
        fun provideDbManager(application: Application): DbManager {
            return DbManager(application)
        }

        @Provides
        @Singleton
        fun provideCocktailsDao(cocktailsDatabase: DbManager): CocktailsDao {
            return cocktailsDatabase.getDao()
        }

        @Provides
        fun provideMapper(): CocktailMapper {
            return CocktailMapper()
        }

        @Provides
        fun provideCocktailsRepositories(cocktailsDao: CocktailsDao, mapper: CocktailMapper): CocktailRepository =
            CocktailRepositoryImpl(cocktailsDao, mapper)
    }
}