package com.weather.paris.di

import com.weather.paris.data.database.weather.CacheMapper
import com.weather.paris.data.database.weather.WeatherDao
import com.weather.paris.data.network.weather.NetworkMapper
import com.weather.paris.data.network.weather.WeatherService
import com.weather.paris.data.repositories.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(weatherService: WeatherService,
                                 weatherDao: WeatherDao,
                                 networkMapper: NetworkMapper,
                                 cacheMapper: CacheMapper): WeatherRepository {
        return WeatherRepository(weatherService, weatherDao, networkMapper, cacheMapper)
    }
}