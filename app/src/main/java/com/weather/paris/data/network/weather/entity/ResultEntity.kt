package com.weather.paris.data.network.weather.entity

import com.google.gson.annotations.SerializedName

data class ResultEntity (

    @SerializedName("daily")
    var daily: List<WeatherInfoEntity>

)