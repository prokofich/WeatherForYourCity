package com.example.weatherforyourcity.model.repository

import android.content.Context
import android.widget.Toast
import com.example.weatherforyourcity.model.modelResponse.WeatherModel
import com.example.weatherforyourcity.model.api.RetrofitInstance
import com.example.weatherforyourcity.model.constant.MAIN
import retrofit2.Response

class Repository(private val context: Context) {

    /** асинхронное получение погоды для города */
    suspend fun getWeather(city: String, apikey: String): Response<WeatherModel> {
        return RetrofitInstance.api.getWeather(city,apikey)
    }

    /** закрытие приложения */
    fun exitingApplication(){
        MAIN.finishAffinity()
    }

    /** функция показа всплывающего сообщения */
    fun showToast(message: String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}