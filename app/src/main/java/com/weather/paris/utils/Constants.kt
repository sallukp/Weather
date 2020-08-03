package com.weather.paris.utils

object Constants {

    const val INDEX_KEY = "index"
    const val WEATHER_URL = "https://api.openweathermap.org/data/2.5/"
    const val ICON_URL_PREFIX = "https://openweathermap.org/img/wn/"
    const val ICON_URL_SUFFIX = "@2x.png"

    // latitude and longitude of Paris
    const val PARIS_LATITUDE = 48.85f
    const val PARIS_LONGITUDE = 2.35f

    const val METRIC_UNIT = "metric"
    const val EXCLUDE = "{hourly,minutely}"
}