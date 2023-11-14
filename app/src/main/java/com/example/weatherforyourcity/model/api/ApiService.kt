package com.example.weatherforyourcity.model.api

import com.example.weatherforyourcity.model.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather")
    suspend fun getWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): Response<WeatherModel>

}