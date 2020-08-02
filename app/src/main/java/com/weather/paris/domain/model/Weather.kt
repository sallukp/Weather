package com.weather.paris.domain.model

data class Weather
private constructor(var id: Int, var date: Long, var temperature: Temperatures,
                    var feelsLike: Temperatures, var sunrise: Long, var sunset: Long,
                    var windSpeed: Float, val windDirection: Float, var humidity: Float,
                    var pressure: Float, var uvIndex: Int, var dewPoint: Float,
                    var weatherDescription: String, var weatherIcon: String, var rain: Float?, var pop: Float,
                    var snow: Float?, var highTemperature: Float, var lowTemperature: Float ) {

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
        private var uvIndex: Int = 0
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

        fun setUV(uv: Int): Builder {
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