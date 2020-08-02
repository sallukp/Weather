package com.weather.paris.data.network.weather.entity

import com.google.gson.annotations.SerializedName

data class WeatherEntity (

    @SerializedName("id")
    var id: Int,

    @SerializedName("main")
    var forecastMain: String,

    @SerializedName("description")
    var forecast: String,

    @SerializedName("icon")
    var icon: String

)