package com.intercom.parisweather.data.network.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("daily")
    suspend fun getWeather(@Query("q") city: String = "Paris",
                           @Query("cnt") count: Int)
}