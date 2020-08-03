package com.weather.paris.data.network.weather

import com.weather.paris.data.network.weather.entity.WeatherInfoEntity
import com.weather.paris.domain.model.Temperatures
import com.weather.paris.domain.model.Weather
import com.weather.paris.utils.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor(): EntityMapper<WeatherInfoEntity, Weather> {

    override fun mapFromEntity(entity: WeatherInfoEntity): Weather {
        val temperatures = Temperatures(entity.temperature.day, entity.temperature.night,
            entity.temperature.morning, entity.temperature.evening)
        val feelLike = Temperatures(entity.feelsLike.day, entity.feelsLike.night,
            entity.feelsLike.morning, entity.feelsLike.evening)
        return Weather.Builder(entity.index)
            .setDate(entity.date)
            .setTemperatures(temperatures)
            .setFeelsLike(feelLike)
            .setExtremeTemperatures(entity.temperature.max, entity.temperature.min)
            .setSunrise(entity.sunrise)
            .setSunset(entity.sunset)
            .setWeatherDescription(entity.weather.joinToString(". ") {  it.forecast })
            .setWeatherIcon(entity.weather[0].icon)
            .setWind(entity.windSpeed, entity.windDirection)
            .setPressure(entity.pressure)
            .setHumidity(entity.humidity)
            .setDewPoint(entity.dewPoint)
            .setUV(entity.uvIndex)
            .setRain(entity.rain, entity.pop)
            .setSnow(entity.snow)
            .build()
    }

    override fun mapToEntity(domainModel: Weather): WeatherInfoEntity {
        TODO("Not yet implemented")
    }

    fun mapFromEntityList(list: List<WeatherInfoEntity>): List<Weather> = list.map {
        // index of the list is taken as id
        it.index = list.indexOf(it)
        mapFromEntity(it)
    }

}