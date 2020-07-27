package co.jeanpidev.openweatherapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Temperature(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val temperatureMin: Double,
    @SerializedName("temp_max")
    val temperatureMax: Double,
    val pressure: Int,
    val humidity: Int
): Parcelable
