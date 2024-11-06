package com.example.weatherforyourcity.model.modelResponse
import androidx.annotation.Keep

@Keep
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)