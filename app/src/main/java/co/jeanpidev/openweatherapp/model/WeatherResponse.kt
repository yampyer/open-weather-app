package co.jeanpidev.openweatherapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse(
    @SerializedName("cod")
    val code: String? = null,
    val message: String? = null,
    val timezone: Int? = null,
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("coord")
    val coordinates: Coordinates? = null,
    val weather: List<Weather>? = null,
    @SerializedName("main")
    val temperature: Temperature? = null,
    val wind: Wind? = null,
    @SerializedName("sys")
    val additionalInfo: AdditionalInfo? = null
): Parcelable
