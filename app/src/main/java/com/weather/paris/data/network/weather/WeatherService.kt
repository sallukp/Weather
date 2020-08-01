package com.weather.paris.data.network.weather

import com.weather.paris.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("daily")
    suspend fun getWeather(@Query("q") city: String = "Paris",
                           @Query("cnt") count: Int,
                           @Query("appid") appId: String = BuildConfig.weatherApiKey)
}