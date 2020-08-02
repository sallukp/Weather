package com.weather.paris.data.database.weather

import com.weather.paris.domain.model.Temperatures
import com.weather.paris.domain.model.Weather
import com.weather.paris.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<WeatherCacheEntity, Weather> {

    override fun mapFromEntity(entity: WeatherCacheEntity): Weather {
        val temperatures = Temperatures(entity.dayTemperature, entity.nightTemperature,
            entity.morningTemperature, entity.eveningTemperature)
        val feelsLike = Temperatures(entity.dayFeelLike, entity.nightFeelLike,
            entity.morningFeelLike, entity.eveningFeelLike)
        return Weather.Builder(entity.id)
            .setDate(entity.date)
            .setTemperatures(temperatures)
            .setFeelsLike(feelsLike)
            .setSunrise(entity.sunrise)
            .setSunset(entity.sunset)
            .setWind(entity.windSpeed, entity.windDirection)
            .setHumidity(entity.humidity)
            .setPressure(entity.pressure)
            .setUV(entity.uvIndex)
            .setDewPoint(entity.dewPoint)
            .setWeatherDescription(entity.weatherDescription)
            .setWeatherIcon(entity.weatherIcon)
            .setRain(entity.rain, entity.pop)
            .setSnow(entity.snow)
            .setExtremeTemperatures(entity.highTemperature, entity.lowTemperature)
            .build()
    }

    override fun mapToEntity(domainModel: Weather): WeatherCacheEntity {
        return WeatherCacheEntity.Builder(domainModel.id)
            .setDate(domainModel.date)
            .setDayTemperature(domainModel.temperature.day)
            .setNightTemperature(domainModel.temperature.night)
            .setMorningTemperature(domainModel.temperature.morning)
            .setEveningTemperature(domainModel.temperature.evening)
            .setDayFeelLike(domainModel.feelsLike.day)
            .setNightFeelLike(domainModel.feelsLike.night)
            .setMorningFeelLike(domainModel.feelsLike.morning)
            .setEveningFeelLike(domainModel.feelsLike.evening)
            .setSunrise(domainModel.sunrise)
            .setSunset(domainModel.sunset)
            .setWindSpeed(domainModel.windSpeed)
            .setWindDirection(domainModel.windDirection)
            .setHumidity(domainModel.humidity)
            .setPressure(domainModel.pressure)
            .setUV(domainModel.uvIndex)
            .setDewPoint(domainModel.dewPoint)
            .setWeatherDescription(domainModel.weatherDescription)
            .setWeatherIcon(domainModel.weatherIcon)
            .setRain(domainModel.rain)
            .setPop(domainModel.pop)
            .setSnow(domainModel.snow)
            .setHighTemperature(domainModel.highTemperature)
            .setLowTemperature(domainModel.lowTemperature)
            .build()
    }

    fun mapFromEntities(list: List<WeatherCacheEntity>): List<Weather>
            = list.map { mapFromEntity(it) }

}