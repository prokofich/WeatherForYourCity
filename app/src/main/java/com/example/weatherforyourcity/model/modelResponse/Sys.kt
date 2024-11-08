package com.example.weatherforyourcity.model.modelResponse
import androidx.annotation.Keep

@Keep
data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)