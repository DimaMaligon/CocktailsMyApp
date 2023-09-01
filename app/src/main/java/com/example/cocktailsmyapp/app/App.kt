package com.example.cocktailsmyapp.app

import android.app.Application
import com.example.cocktailsmyapp.data.DbManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var dbManager: com.example.cocktailsmyapp.data.DbManager

    override fun onCreate() {
        super.onCreate()
        dbManager.openDb()
    }
}