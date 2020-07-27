package co.jeanpidev.openweatherapp.network

import co.jeanpidev.openweatherapp.model.WeatherResponse
import co.jeanpidev.openweatherapp.utils.PARAM_LAT
import co.jeanpidev.openweatherapp.utils.PARAM_LONG
import co.jeanpidev.openweatherapp.utils.PARAM_QUERY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherByCityName(@Query(PARAM_QUERY) query: String?): Response<WeatherResponse>

    @GET("weather")
    suspend fun getWeatherByCoordinates(@Query(PARAM_LAT) lat: Double?, @Query(PARAM_LONG) long: Double?): Response<WeatherResponse>
}
