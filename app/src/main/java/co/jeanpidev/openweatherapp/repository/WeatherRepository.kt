package co.jeanpidev.openweatherapp.repository

import androidx.lifecycle.MutableLiveData
import co.jeanpidev.openweatherapp.model.Coordinates
import co.jeanpidev.openweatherapp.model.WeatherResponse
import co.jeanpidev.openweatherapp.network.WeatherApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi): BaseRepository() {

    suspend fun getWeatherByCity(cityName: String): MutableLiveData<Result<WeatherResponse>> {
        val result = MutableLiveData<Result<WeatherResponse>>()
        result.value = Result.loading(true)
        val response = weatherApi.getWeatherByCityName(cityName)
        response.body()?.let { weather ->
            result.value = handleSuccess(weather)
        }
        response.errorBody()?.let {
            result.value = handleError(it)
        }

        return result
    }

    suspend fun getWeatherByCoordinates(coords: Coordinates): MutableLiveData<Result<WeatherResponse>> {
        val result = MutableLiveData<Result<WeatherResponse>>()
        result.value = Result.loading(true)
        val response = weatherApi.getWeatherByCoordinates(coords.latitude, coords.longitude)
        response.body()?.let { weather ->
            result.value = handleSuccess(weather)
        }
        response.errorBody()?.let {
            result.value = handleError(it)
        }

        return result
    }
}
