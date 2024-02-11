package com.example.weatherforyourcity.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforyourcity.model.WeatherModel
import com.example.weatherforyourcity.model.constant.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SecondFragmentViewModel:ViewModel() {

    val weatherInCity : MutableLiveData<Response<WeatherModel>?> = MutableLiveData()
    private var answer:Response<WeatherModel>? = null

    //функция перехода к первому экрану
    fun goToFirstFragment(){
        repository.showFirstFragment()
    }

    //функция асинхронного получение погоды для конкретного города
    fun getWeatherInCity(context: Context,city:String,apikey:String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                answer = repository.getWeather(city, apikey)
                withContext(Dispatchers.Main){
                    weatherInCity.value = answer
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main) {
                    repository.showToast(context,"The city name is entered incorrectly!")
                    goToFirstFragment()
                }
            }
        }
    }

    //функция показа всплывающего сообщения
    fun showToast(context: Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

}