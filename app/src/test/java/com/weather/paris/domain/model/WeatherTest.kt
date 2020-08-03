package com.weather.paris.domain.model
import org.junit.Assert.*
import org.junit.Test


class WeatherTest {

    val weatherBuilder = Weather.Builder(1)

    val temperatures = Temperatures()

    @Test
    fun testWindowInfo() {
        weatherBuilder.setTemperatures(temperatures)
        weatherBuilder.setFeelsLike(temperatures)
        weatherBuilder.setWeatherDescription("")
        weatherBuilder.setWeatherIcon("")
        assertEquals(weatherBuilder.setWind(2.8f, 0f).build().windInfo(), "2.8m/s N")
        assertEquals(weatherBuilder.setWind(3f, 90f).build().windInfo(), "3m/s E")
        assertEquals(weatherBuilder.setWind(3.2f, 180f).build().windInfo(), "3.2m/s S")
        assertEquals(weatherBuilder.setWind(1.5f, 270f).build().windInfo(), "1.5m/s W")
        assertEquals(weatherBuilder.setWind(3f, 340f).build().windInfo(), "3m/s NNW")
        assertEquals(weatherBuilder.setWind(8f, 308f).build().windInfo(), "8m/s NW")
        assertEquals(weatherBuilder.setWind(3f, 300f).build().windInfo(), "3m/s WNW")
        assertEquals(weatherBuilder.setWind(4f, 164f).build().windInfo(), "4m/s SSE")
        assertEquals(weatherBuilder.setWind(2.5f, 52.5f).build().windInfo(), "2.5m/s NE")
        assertEquals(weatherBuilder.setWind(2f, 208f).build().windInfo(), "2m/s SSW")
    }

    @Test
    fun testRainInfo() {
        weatherBuilder.setTemperatures(temperatures)
        weatherBuilder.setFeelsLike(temperatures)
        weatherBuilder.setWeatherDescription("")
        weatherBuilder.setWeatherIcon("")
        assertEquals(weatherBuilder.setRain(null, 0f).build().rainInfo(), "0%")
        assertEquals(weatherBuilder.setRain(1.9f, 0.52f).build().rainInfo(), "1.9mm/h (52%)")
        assertEquals(weatherBuilder.setRain(2f, 0.6f).build().rainInfo(), "2mm/h (60%)")
    }


    @Test
    fun testTemperatureInfo() {
        weatherBuilder.setTemperatures(temperatures)
        weatherBuilder.setFeelsLike(temperatures)
        weatherBuilder.setWeatherDescription("")
        weatherBuilder.setWeatherIcon("")
        assertEquals(weatherBuilder
            .setExtremeTemperatures(26f, 22.3f)
            .build()
            .temperatureInfo(), "The high will be 26°C, the low will be 22°C.")
        assertEquals(weatherBuilder
            .setExtremeTemperatures(26.6f, 28f)
            .build()
            .temperatureInfo(), "The high will be 28°C, the low will be 27°C.")
    }

    @Test
    fun testWeatherIconUrl() {
        weatherBuilder.setTemperatures(temperatures)
        weatherBuilder.setFeelsLike(temperatures)
        weatherBuilder.setWeatherDescription("")
        weatherBuilder.setWeatherIcon("01")
        assertEquals(weatherBuilder.build()
            .weatherIconUrl(), "https://openweathermap.org/img/wn/01@2x.png")
    }

}