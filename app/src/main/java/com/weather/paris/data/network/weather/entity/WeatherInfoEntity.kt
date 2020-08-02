package com.weather.paris.data.network.weather.entity

import com.google.gson.annotations.SerializedName

data class WeatherInfoEntity (

    @SerializedName("dt")
    var date: Long,

    @SerializedName("sunrise")
    var sunrise: Long,

    @SerializedName("sunset")
    var sunset: Long,

    // Temperature in celsius
    @SerializedName("temp")
    var temperature: TemperatureEntity,

    // Temperature in celsius
    @SerializedName("feels_like")
    var feelsLike: TemperatureEntity,

    // Atmospheric pressure on the sea level, Unit: hPa
    @SerializedName("pressure")
    var pressure: Float,

    // Humidity, Unit: %
    @SerializedName("humidity")
    var humidity: Float,

    @SerializedName("weather")
    var weather: List<WeatherEntity>,

    // Atmospheric temperature (varying according to pressure and humidity) below which water
    // droplets begin to condense and dew can form. Unit – Celsius
    @SerializedName("dew_point")
    var dewPoint: Float,

    // Wind speed. Unit: meter/sec
    @SerializedName("wind_speed")
    var windSpeed: Float,

    // Wind direction, Unit: degrees
    @SerializedName("wind_deg")
    var windDirection: Float,

    // Cloudiness, Unit: %
    @SerializedName("clouds")
    var clouds: Float,

    // Midday UV index
    @SerializedName("uvi")
    var uvIndex: Int,

    // Probability of precipitation
    @SerializedName("pop")
    var pop: Float,

    // Optional; Precipitation volume, Unit: mm
    @SerializedName("rain")
    var rain: Float?,

    //  Optional; Snow volume, Unit: mm
    @SerializedName("snow")
    var snow: Float?
) {
    var index = 0
}