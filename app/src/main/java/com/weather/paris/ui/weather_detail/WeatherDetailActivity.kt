package com.weather.paris.ui.weather_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.RequestManager
import com.weather.paris.R
import com.weather.paris.utils.Constants
import com.weather.paris.utils.Extension.toCelsius
import com.weather.paris.utils.Extension.toDate
import com.weather.paris.utils.Extension.toHPa
import com.weather.paris.utils.Extension.toPercentage
import com.weather.paris.utils.Extension.toTime
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_weather_detail.*
import kotlinx.android.synthetic.main.layout_daily_weather_item.*
import kotlinx.android.synthetic.main.layout_daily_weather_item.view.*
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class WeatherDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var glideRequests: RequestManager
    private val TAG = "WeatherDetailActivity"
    private val viewModel: WeatherDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        subscribeObservers()
        val position = intent.getIntExtra(Constants.INDEX_KEY, -1)
        if (position > -1) {
            viewModel.setStateEvent(WeatherDetailsViewModel.WeatherDetailStateEvent
                .GetWeatherDetailsEvent(position))
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer {weather ->
            title_text.text = weather.weatherDescription.capitalize()
            subtitle_text.text = weather.temperatureInfo()
            date_text.text = weather.date.toDate()
            glideRequests.load(weather.weatherIconUrl()).into(weather_icon)

            morning_temperature.text = weather.temperature.morning.toCelsius()
            afternoon_temperature.text = weather.temperature.day.toCelsius()
            evening_temperature.text = weather.temperature.evening.toCelsius()
            night_temperature.text = weather.temperature.night.toCelsius()
            morning_feels_like.text = weather.feelsLike.morning.toCelsius()
            afternoon_feels_like.text = weather.temperature.day.toCelsius()
            evening_feels_like.text = weather.feelsLike.evening.toCelsius()
            night_feels_like.text = weather.temperature.night.toCelsius()

            wind_value.text = weather.windInfo()
            pressure_value.text = weather.pressure.toHPa()
            dew_point_value.text = weather.dewPoint.toCelsius()
            humidity_value.text = weather.humidity.toPercentage()
            uv_value.text = weather.uvIndex.roundToInt().toString()
            rain_title.text = weather.rainInfo()

            sunrise_value.text = weather.sunrise.toTime()
            sunset_value.text = weather.sunset.toTime()
        })
    }
}
