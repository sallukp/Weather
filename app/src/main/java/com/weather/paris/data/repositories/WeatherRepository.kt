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
constructor(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao,
    private val networkMapper: NetworkMapper,
    private val cacheMapper: CacheMapper) {

    var dailyWeatherList:  List<Weather> = listOf()

    suspend fun getWeatherForFiveDays(): Flow<DataState<List<Weather>>> = flow{
        val lastMidnight = System.currentTimeMillis().toMidnight()
        val fiveDaysAfterLastMidnight = lastMidnight + 432000000L   // 432000000L = 5*24*60*60*1000
        // we need the weather forecast only for last 5 days, time stamps is stored in database in seconds
        val weatherList = weatherDao.getWeather(lastMidnight / 1000, fiveDaysAfterLastMidnight / 1000)
        if (weatherList.isNotEmpty()) {
            dailyWeatherList = cacheMapper.mapFromEntities(weatherList)
            emit(DataState.Success(dailyWeatherList))
        }
        emit(DataState.Loading)
        try {
            val results = weatherService.getWeather()
            val weatherList = networkMapper.mapFromEntityList(results.daily)
            for (weather in weatherList) {
                weatherDao.insertWeather(cacheMapper.mapToEntity(weather))
            }
            val mutableWeatherList = weatherList.toMutableList()
            val size = mutableWeatherList.size
            // result has 8 days weather forecast, we only need 5 days
            if (size > 5) {
                for(i in size - 1 downTo 5) {
                    mutableWeatherList.removeAt(i)
                }
            }
            dailyWeatherList = mutableWeatherList.toList()
            emit(DataState.Success(dailyWeatherList))
        } catch (ex: Exception) {
            emit(DataState.Error(ex))
        }
    }

    fun getWeatherDetails(position: Int): Weather? =
        if (position >= 0 && position < dailyWeatherList.size) {
            dailyWeatherList[position]
        } else {
            null
        }
}