package co.jeanpidev.openweatherapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import co.jeanpidev.openweatherapp.base.BaseViewModel
import co.jeanpidev.openweatherapp.model.Coordinates
import co.jeanpidev.openweatherapp.model.WeatherResponse
import co.jeanpidev.openweatherapp.repository.WeatherRepository
import co.jeanpidev.openweatherapp.repository.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val weatherRepository: WeatherRepository): BaseViewModel() {

    private val currentWeather = MutableLiveData<Result<WeatherResponse>>()

    private val queryValue = MutableLiveData<String>()

    fun getCurrentWeather() = currentWeather

    fun getQueryValue() = queryValue

    fun getWeatherCity(cityName: String) {
        currentWeather.postValue(Result.loading(true))
        viewModelScope.launch {
            val weather = weatherRepository.getWeatherByCity(cityName)
            currentWeather.postValue(weather.value)
        }
    }

    fun getWeatherLocation(coordinates: Coordinates) {
        currentWeather.postValue(Result.loading(true))
        viewModelScope.launch {
            val weather = weatherRepository.getWeatherByCoordinates(coordinates)
            currentWeather.postValue(weather.value)
        }
    }
}
