package com.weather.paris.ui.weather_list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.weather.paris.data.repositories.WeatherRepository
import com.weather.paris.domain.model.Weather
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

    private val _dataState: MutableLiveData<DataState<List<Weather>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Weather>>>
        get () = _dataState

    fun setStateEvent(weatherStateEvent: WeatherStateEvent) {
        viewModelScope.launch {
            when (weatherStateEvent) {
                is WeatherStateEvent.GetWeatherEvent -> {
                    weatherRepository.getWeatherForFiveDays().onEach { dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class WeatherStateEvent {
        object GetWeatherEvent: WeatherStateEvent()
    }
}