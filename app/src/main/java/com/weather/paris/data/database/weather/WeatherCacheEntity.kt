package com.weather.paris.data.database.weather

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherCacheEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "dt")
    var date: Long,

    @NonNull
    @ColumnInfo(name = "d_t")
    var dayTemperature: Float,

    @NonNull
    @ColumnInfo(name = "n_t")
    var nightTemperature: Float,

    @NonNull
    @ColumnInfo(name = "m_t")
    var morningTemperature: Float,

    @NonNull
    @ColumnInfo(name = "e_t")
    var eveningTemperature: Float,

    @NonNull
    @ColumnInfo(name = "d_f")
    var dayFeelLike: Float,

    @NonNull
    @ColumnInfo(name = "n_f")
    var nightFeelLike: Float,

    @NonNull
    @ColumnInfo(name = "m_f")
    var morningFeelLike: Float,

    @NonNull
    @ColumnInfo(name = "e_f")
    var eveningFeelLike: Float,

    @NonNull
    @ColumnInfo(name = "s_r")
    var sunrise: Long,

    @NonNull
    @ColumnInfo(name = "s_s")
    var sunset: Long,

    @NonNull
    @ColumnInfo(name = "win_s")
    var windSpeed: Float,

    @NonNull
    @ColumnInfo(name = "win_d")
    var windDirection: Float,

    @NonNull
    @ColumnInfo(name = "hmd")
    var humidity: Float,

    @NonNull
    @ColumnInfo(name = "pressure")
    var pressure: Float,

    @NonNull
    @ColumnInfo(name = "uv")
    var uvIndex: Float,

    @NonNull
    @ColumnInfo(name = "d_p")
    var dewPoint: Float,

    @NonNull
    @ColumnInfo(name = "weather")
    var weatherDescription: String,

    @NonNull
    @ColumnInfo(name = "icon")
    var weatherIcon: String,

    @ColumnInfo(name = "rain")
    var rain: Float?,

    @NonNull
    @ColumnInfo(name = "pop")
    var pop: Float,

    @ColumnInfo(name = "snow")
    var snow: Float?,

    @NonNull
    @ColumnInfo(name = "h_t")
    var highTemperature: Float,

    @NonNull
    @ColumnInfo(name = "l_t")
    var lowTemperature: Float
) {
    class Builder(val id: Int) {

        var date: Long = 0
        var dayTemperature: Float = 0f
        var nightTemperature: Float = 0f
        var morningTemperature: Float = 0f
        var eveningTemperature: Float = 0f
        var dayFeelLike: Float = 0f
        var nightFeelLike: Float = 0f
        var morningFeelLike: Float = 0f
        var eveningFeelLike: Float = 0f
        var sunrise: Long = 0
        var sunset: Long = 0
        var windSpeed: Float = 0f
        var windDirection: Float = 0f
        var humidity: Float = 0f
        var pressure: Float = 0f
        var uvIndex: Float = 0f
        var dewPoint: Float = 0f
        lateinit var weatherDescription: String
        lateinit var weatherIcon: String
        var rain: Float? = null
        var pop: Float = 0f
        var snow: Float? = null
        var highTemperature: Float = 0f
        var lowTemperature: Float = 0f

        fun build(): WeatherCacheEntity {
            return WeatherCacheEntity(id, date, dayTemperature, nightTemperature, morningTemperature,
                eveningTemperature, dayFeelLike, nightFeelLike, morningFeelLike, eveningFeelLike, sunrise,
                sunset, windSpeed, windDirection, humidity, pressure, uvIndex, dewPoint,
                weatherDescription, weatherIcon, rain, pop, snow, highTemperature, lowTemperature)
        }

        fun setDate(date: Long): Builder {
            this.date = date
            return this
        }

        fun setDayTemperature(day: Float): Builder {
            this.dayTemperature = day
            return this
        }

        fun setNightTemperature(night: Float): Builder {
            this.nightTemperature = night
            return this
        }

        fun setMorningTemperature(morning: Float): Builder {
            this.morningTemperature = morning
            return this
        }

        fun setEveningTemperature(evening: Float): Builder {
            this.eveningTemperature = evening
            return this
        }

        fun setDayFeelLike(day: Float): Builder {
            this.dayFeelLike = day
            return this
        }

        fun setNightFeelLike(night: Float): Builder {
            this.nightFeelLike = night
            return this
        }

        fun setMorningFeelLike(morning: Float): Builder {
            this.morningFeelLike = morning
            return this
        }

        fun setEveningFeelLike(evening: Float): Builder {
            this.eveningFeelLike = evening
            return this
        }

        fun setSunrise(sunrise: Long): Builder {
            this.sunrise = sunrise
            return this
        }

        fun setSunset(sunset: Long): Builder {
            this.sunset = sunset
            return this
        }

        fun setWindSpeed(windSpeed: Float): Builder {
            this.windSpeed = windSpeed
            return this
        }

        fun setWindDirection(windDirection: Float): Builder {
            this.windDirection = windDirection
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

        fun setUV(uvIndex: Float): Builder {
            this.uvIndex = uvIndex
            return this
        }

        fun setDewPoint(dewPoint: Float): Builder {
            this.dewPoint = dewPoint
            return this
        }

        fun setWeatherDescription(weatherDescription: String): Builder {
            this.weatherDescription = weatherDescription
            return this
        }

        fun setWeatherIcon(weatherIcon: String): Builder {
            this.weatherIcon = weatherIcon
            return this
        }

        fun setRain(rain: Float?): Builder {
            this.rain = rain
            return this
        }

        fun setPop(pop: Float): Builder {
            this.pop = pop
            return this
        }

        fun setSnow(snow: Float?): Builder {
            this.snow = snow
            return this
        }

        fun setHighTemperature(highTemperature: Float): Builder {
            this.highTemperature = highTemperature
            return this
        }

        fun setLowTemperature(lowTemperature: Float): Builder {
            this.lowTemperature = lowTemperature
            return this
        }
    }
}