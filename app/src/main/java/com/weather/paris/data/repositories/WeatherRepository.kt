package com.weather.paris.data.repositories

import com.weather.paris.data.database.weather.CacheMapper
import com.weather.paris.data.database.weather.WeatherDao
import com.weather.paris.data.network.weather.NetworkMapper
import com.weather.paris.data.network.weather.WeatherService
import com.weather.paris.domain.model.Weather
import com.weather.paris.utils.DataState
import com.weather.paris.utils.Extension.toMidnight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class WeatherRepository
@Inject
constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao,
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper) {

    suspend fun getWeatherForFiveDays(): Flow<DataState<List<Weather>>> = flow{
        emit(DataState.Loading)
        try {
            val results = weatherService.getWeather()
            val weatherList = networkMapper.mapFromEntityList(results.daily)
            for (weather in weatherList) {
                weatherDao.insertWeather(cacheMapper.mapToEntity(weather))
            }
            val mutableWeatherList = weatherList.toMutableList()
            val size = mutableWeatherList.size
            // result has 7 days weather forecast, we only need 5 days
            if (size > 5) {
                for(i in 5..size) {
                    mutableWeatherList.removeAt(i)
                }
            }
            emit(DataState.Success(mutableWeatherList.toList()))
        } catch (ex: Exception) {
            val lastMidnight = System.currentTimeMillis().toMidnight()
            val fiveDaysAfterLastMidnight = lastMidnight + 432000000L   // 432000000L = 5*24*60*60*1000
            // we need the weather forecast only for last 5 days
            val weatherList = weatherDao.getWeather(lastMidnight, fiveDaysAfterLastMidnight)
            when(weatherList.size) {
                0 -> emit(DataState.Error(ex))
                else -> {
                    emit(DataState.Success(cacheMapper.mapFromEntities(weatherList)))
                }
            }
        }
    }
}