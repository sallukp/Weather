package com.weather.paris.domain.model

import com.weather.paris.utils.Constants
import com.weather.paris.utils.Extension.trimTrailingZero
import kotlin.math.roundToInt

data class Weather
private constructor(var id: Int) {

    var date: Long = 0;
    lateinit var temperature: Temperatures
    lateinit var feelsLike: Temperatures
    var sunrise: Long = 0
    var sunset: Long = 0
    lateinit var weatherDescription: String
    lateinit var weatherIcon: String
    var windSpeed: Float = 0f
    var windDirection: Float = 0f
    var humidity: Float = 0f
    var pressure: Float = 0f
    var uvIndex: Float = 0f
    var dewPoint: Float = 0f
    var rain: Float? = null
    var pop: Float = 0f
    var snow: Float? = null
    var highTemperature: Float = 0f
    var lowTemperature: Float = 0f

    constructor(id: Int, date: Long,  temperature: Temperatures,
                   feelsLike: Temperatures,  sunrise: Long,  sunset: Long,
                   windSpeed: Float,  windDirection: Float,  humidity: Float,
                   pressure: Float,  uvIndex: Float,  dewPoint: Float,
                   weatherDescription: String,  weatherIcon: String,  rain: Float?,
                   pop: Float,  snow: Float?,  highTemperature: Float,
                   lowTemperature: Float ): this(id) {
        this.date = date
        this.temperature = temperature
        this.feelsLike = feelsLike
        this.sunrise = sunrise
        this.sunset = sunset
        this.windSpeed = windSpeed
        this.windDirection = windDirection
        this.humidity = humidity
        this.pressure = pressure
        this.uvIndex = uvIndex
        this.dewPoint = dewPoint
        this.weatherDescription = weatherDescription
        this.weatherIcon = weatherIcon
        this.rain = rain
        this.pop = pop
        this.snow = snow
        this.highTemperature = highTemperature
        this.lowTemperature = lowTemperature
    }

    fun windInfo(): String {
        val index = (windDirection / 22.5f + .5f).toInt()
        val directions = arrayOf(
            "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW",
            "NW", "NNW"
        )
        return "${windSpeed.trimTrailingZero()}m/s ${directions[index % 16]}"
    }

    fun rainInfo():String = if (rain == null) {
        "${(pop * 100).toInt()}%"
    } else {
        "${rain!!.trimTrailingZero()}mm/h (${(pop * 100).toInt()}%)"
    }

    fun temperatureInfo(): String = if (highTemperature >= lowTemperature) {
        "The high will be ${highTemperature.roundToInt()}°C, the low will be ${lowTemperature.roundToInt()}°C."
    } else {
        "The high will be ${lowTemperature.roundToInt()}°C, the low will be ${highTemperature.roundToInt()}°C."
    }

    fun weatherIconUrl(): String = "${Constants.ICON_URL_PREFIX}${weatherIcon}${Constants.ICON_URL_SUFFIX}"

    class Builder(private val id: Int) {
        private var date: Long = 0;
        private lateinit var temperature: Temperatures
        private lateinit var feelsLike: Temperatures
        private var sunrise: Long = 0
        private var sunset: Long = 0
        private lateinit var weatherDescription: String
        private lateinit var weatherIcon: String
        private var windSpeed: Float = 0f
        private var windDirection: Float = 0f
        private var humidity: Float = 0f
        private var pressure: Float = 0f
        private var uvIndex: Float = 0f
        private var dewPoint: Float = 0f
        private var rain: Float? = null
        private var pop: Float = 0f
        private var snow: Float? = null
        private var highTemperature: Float = 0f
        private var lowTemperature: Float = 0f

        fun setDate(date: Long): Builder {
            this.date = date
            return this
        }

        fun setSunrise(sunrise: Long): Builder {
            this.sunrise = sunrise
            return this
        }

        fun setSunset(sunset: Long): Builder {
            this.sunset = sunrise
            return this
        }

        fun setTemperatures(temperature: Temperatures): Builder {
            this.temperature = temperature
            return this
        }

        fun setFeelsLike(feelsLike: Temperatures): Builder {
            this.feelsLike = feelsLike
            return this
        }

        fun setWeatherDescription(weatherDesc: String): Builder {
            this.weatherDescription = weatherDesc
            return this
        }

        fun setWeatherIcon(icon: String): Builder {
            this.weatherIcon = icon
            return this
        }

        fun setWind(speed: Float, direction: Float): Builder {
            this.windSpeed = speed
            this.windDirection = direction
            return this
        }

        fun setHumidity(humidity: Float): Builder {
            this.humidity = humidity
            return this
        }

        fun setPressure(pressure: Float): Builder {
            this.pressure = pressure
            return this
        }

        fun setUV(uv: Float): Builder {
            this.uvIndex = uv
            return this
        }

        fun setDewPoint(dewPoint: Float): Builder {
            this.dewPoint = dewPoint
            return this
        }

        fun setExtremeTemperatures(high: Float, low: Float): Builder {
            this.highTemperature = high
            this.lowTemperature = low
            return this
        }

        fun setSnow(snow: Float?): Builder {
            this.snow = snow
            return this
        }

        fun setRain(rain: Float?, pop: Float): Builder {
            this.rain = rain
            this.pop = pop
            return this
        }

        fun build(): Weather {
            return Weather(id, date, temperature, feelsLike, sunrise, sunset, windSpeed,
                windDirection, humidity, pressure, uvIndex, dewPoint, weatherDescription,
                weatherIcon, rain, pop, snow, highTemperature, lowTemperature)
        }
    }

}