package com.example.weatherforyourcity.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherforyourcity.model.modelResponse.WeatherModel
import com.example.weatherforyourcity.model.constant.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SecondFragmentViewModel(application: Application) : AndroidViewModel(application) {

    val weatherInCity: MutableLiveData<Response<WeatherModel>?> = MutableLiveData()
    private var answer: Response<WeatherModel>? = null

    var navToFirstFragment: (() -> Unit)? = null

    /** функция перехода к первому экрану */
    fun goToFirstFragment(){
        navToFirstFragment?.invoke()
    }

    /** функция асинхронного получение погоды для конкретного города */
    fun getWeatherInCity(city: String, apikey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                answer = repository.getWeather(city, apikey)
                withContext(Dispatchers.Main){
                    weatherInCity.value = answer
                }
            }
            catch (e:Exception){
                withContext(Dispatchers.Main) {
                    repository.showToast("The city name is entered incorrectly!")
                    goToFirstFragment()
                }
            }
        }
    }

    /** функция показа всплывающего сообщения */
    fun showToast(message: String) = Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()

}