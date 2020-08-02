package com.weather.paris.di

import android.content.Context
import androidx.room.Room
import com.weather.paris.data.database.weather.WeatherDao
import com.weather.paris.data.database.weather.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface DatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase = Room
        .databaseBuilder(context,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao = database.weatherDao()
}