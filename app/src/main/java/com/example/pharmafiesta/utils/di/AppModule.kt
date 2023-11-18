package com.example.pharmafiesta.utils.di

import android.content.Context
import androidx.room.Room
import com.example.pharmafiesta.data.local.database.DrugsDatabase
import com.example.pharmafiesta.utils.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDrugsDatabase(@ApplicationContext appContext: Context): DrugsDatabase {
        return Room.databaseBuilder(
            appContext, DrugsDatabase::class.java,
            "drugs.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext appContext: Context): UserPreferences {
        return UserPreferences(appContext)
    }
}