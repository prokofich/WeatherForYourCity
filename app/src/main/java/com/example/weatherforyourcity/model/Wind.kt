package com.example.weatherforyourcity.model
import androidx.annotation.Keep

@Keep
data class Wind(
    val deg: Int,
    val speed: Double
)