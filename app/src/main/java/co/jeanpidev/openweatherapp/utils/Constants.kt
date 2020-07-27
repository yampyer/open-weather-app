package co.jeanpidev.openweatherapp.utils

const val SPLASH_TIME_OUT: Long = 3000

// region extras
const val EXTRA_CURRENT_WEATHER: String = "current_weather"
// endregion

// region api errors
const val ERROR_UNKNOWN = "An error has occurred"
const val ERROR_NOT_FOUND = "city not found"
// endregion

// region tags
const val ERROR_TAG = "Error"
// endregion

// region urls
const val TEST_PORT = 8080
const val BASE_URL_TEST = "http://localhost:$TEST_PORT/"
const val IMAGES_URL = "https://openweathermap.org/img/wn/"
const val IMAGE_EXTENSION = "@2x.png"
const val PARAM_APP_ID = "appid"
const val PARAM_QUERY = "q"
const val PARAM_LAT = "lat"
const val PARAM_LONG = "lon"
const val PARAM_UNITS = "units"
const val METRIC_SYSTEM = "metric"
// endregion
