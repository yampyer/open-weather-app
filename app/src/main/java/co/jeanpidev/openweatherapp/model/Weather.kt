package co.jeanpidev.openweatherapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val id: Int,
    @SerializedName("main")
    val status: String,
    val description: String,
    val icon: String
): Parcelable
