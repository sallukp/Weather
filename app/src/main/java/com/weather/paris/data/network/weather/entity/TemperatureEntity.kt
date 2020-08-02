package com.weather.paris.data.network.weather.entity

import com.google.gson.annotations.SerializedName

data class TemperatureEntity (

    @SerializedName("day")
    var day: Float,

    @SerializedName("min")
    var min: Float,

    @SerializedName("max")
    var max: Float,

    @SerializedName("night")
    var night: Float,

    @SerializedName("eve")
    var evening: Float,

    @SerializedName("morn")
    var morning: Float

)