package com.example.weatherforyourcity.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforyourcity.model.modelResponse.WeatherModel
import com.example.weatherforyourcity.model.constant.LIST_NAME_CITY_FOR_FIRST_FRAGMENT
import com.example.weatherforyourcity.model.constant.WEATHER
import com.example.weatherforyourcity.model.constant.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FirstFragmentViewModel : ViewModel() {

    val weatherInCity: MutableLiveData<Response<WeatherModel>> = MutableLiveData()

    var numberCity = 0

    var navToSecondFragment: ((Bundle?) -> Unit)? = null

    /** закрытие приложения */
    fun exitingApplication() = repository.exitingApplication()

    /** переход ко второму экрану */
    fun goToSecondFragment(bundle: Bundle?){
        navToSecondFragment?.invoke(bundle)
    }

    /** переход ко второму экрану для показа дополнительной информации */
    fun goToSecondFragmentForShowInformation(nameCity: String) {
        val bundle = Bundle()
        bundle.putString(WEATHER,nameCity)
        navToSecondFragment?.invoke(bundle)
    }

    /** получение погоды для ряда городов */
    fun getPopularWeatherInCity(){
        viewModelScope.launch(Dispatchers.IO) {
            val listCity = LIST_NAME_CITY_FOR_FIRST_FRAGMENT.shuffled().slice(0..2)
            for(city in listCity){
                val answer = repository.getWeather(city = city, apikey = "5add6a6a1179b651037584ea12d07431")
                withContext(Dispatchers.Main){
                    numberCity+=1
                    weatherInCity.value = answer
                }
            }
        }
    }

}