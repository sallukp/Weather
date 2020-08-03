package com.weather.paris.ui.weather_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.weather.paris.data.repositories.WeatherRepository
import com.weather.paris.domain.model.Weather
import com.weather.paris.utils.Constants
import com.weather.paris.utils.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class WeatherListViewModel
@ViewModelInject constructor(
    private val weatherRepository: WeatherRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val tag = "WeatherListViewModel"

    private var _dataState: MutableLiveData<DataState<List<Weather>>> = MutableLiveData()
    private var _savedState: MutableLiveData<Boolean> = savedStateHandle.getLiveData(STATE_KEY)
    val dataState: LiveData<DataState<List<Weather>>>
        get () = _dataState

    fun setStateEvent(weatherStateEvent: WeatherStateEvent) {
        viewModelScope.launch {
            when (weatherStateEvent) {
                is WeatherStateEvent.GetWeatherEvent -> {
                    if (_savedState.value == null) {
                        requestWeather()
                    }
                    _savedState.value = true
                }
                is WeatherStateEvent.RefreshWeatherEvent -> {
                    requestWeather()
                }
            }
        }
    }

    private fun requestWeather() {
        Log.d(tag, "requestWeather")
        viewModelScope.launch {
            weatherRepository.getWeatherForFiveDays().onEach { dataState ->
                _dataState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    sealed class WeatherStateEvent {
        object GetWeatherEvent: WeatherStateEvent()
        object RefreshWeatherEvent: WeatherStateEvent()
    }

    companion object {
        const val STATE_KEY = "state"
    }
}