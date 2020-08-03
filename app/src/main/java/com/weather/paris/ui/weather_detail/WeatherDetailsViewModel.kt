package com.weather.paris.ui.weather_detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.weather.paris.data.repositories.WeatherRepository
import com.weather.paris.domain.model.Weather
import kotlinx.coroutines.launch

class WeatherDetailsViewModel
@ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<Weather> = MutableLiveData()
    val dataState: LiveData<Weather>
        get () = _dataState

    fun setStateEvent(weatherDetailStateEvent: WeatherDetailStateEvent) {
        when(weatherDetailStateEvent) {
            is WeatherDetailStateEvent.GetWeatherDetailsEvent -> {
                val position = weatherDetailStateEvent.data
                weatherRepository.getWeatherDetails(position)?.let { weather ->
                    _dataState.value = weather
                }
            }
        }
    }


    sealed class WeatherDetailStateEvent {
        data class GetWeatherDetailsEvent(val data: Int): WeatherDetailStateEvent()
    }
}