package com.weather.paris.data.network.weather

import com.weather.paris.BuildConfig
import com.weather.paris.data.network.weather.entity.ResultEntity
import com.weather.paris.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun getWeather(@Query("lat") lat: Float = Constants.PARIS_LATITUDE,
                           @Query("lon") long: Float = Constants.PARIS_LONGITUDE,
                           @Query("units") unit: String = Constants.METRIC_UNIT,
                           @Query("exclude") exclude: String = Constants.EXCLUDE,
                           @Query("appid") appId: String = BuildConfig.weatherApiKey): ResultEntity
}