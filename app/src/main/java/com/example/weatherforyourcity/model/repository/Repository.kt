package com.example.weatherforyourcity.model.repository

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.weatherforyourcity.R
import com.example.weatherforyourcity.model.WeatherModel
import com.example.weatherforyourcity.model.api.RetrofitInstance
import com.example.weatherforyourcity.model.constant.MAIN
import com.example.weatherforyourcity.model.constant.WEATHER
import retrofit2.Response

class Repository {

    //асинхронное получение погоды для города
    suspend fun getWeather(city:String,apikey:String): Response<WeatherModel> {
        return RetrofitInstance.api.getWeather(city,apikey)
    }

    //показ первого экрана
    fun showFirstFragment(){
        MAIN.navController?.navigate(R.id.action_secondFragment_to_firstFragment)
    }

    //показ второго экрана
    fun showSecondFragment(){
        MAIN.navController?.navigate(R.id.action_firstFragment_to_secondFragment)
    }

    //показ второго экрана с просмотром дополнительной информации
    fun showSecondFragmentForShowInformation(nameCity: String){
        val bundle = Bundle()
        bundle.putString(WEATHER,nameCity)
        MAIN.navController?.navigate(R.id.action_firstFragment_to_secondFragment,bundle)
    }

    //закрытие приложения
    fun exitingApplication(){
        MAIN.finishAffinity()
    }

    //функция показа всплывающего сообщения
    fun showToast(context: Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}