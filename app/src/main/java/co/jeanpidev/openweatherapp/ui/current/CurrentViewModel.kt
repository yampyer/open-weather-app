package co.jeanpidev.openweatherapp.ui.current

import androidx.lifecycle.MutableLiveData
import co.jeanpidev.openweatherapp.base.BaseViewModel
import co.jeanpidev.openweatherapp.model.WeatherResponse
import javax.inject.Inject

class CurrentViewModel @Inject constructor(): BaseViewModel() {

    private val currentWeather = MutableLiveData<WeatherResponse>()

    fun getCurrentWeather() = currentWeather
}
