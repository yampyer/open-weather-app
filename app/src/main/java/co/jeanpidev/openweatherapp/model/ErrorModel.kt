package co.jeanpidev.openweatherapp.model

data class ErrorModel(
    val message: String,
    val code: String? = null
)
