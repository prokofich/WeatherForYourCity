package com.example.weatherforyourcity.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashViewModel: ViewModel() {

    val flagFinishProgress : MutableLiveData <Boolean> = MutableLiveData()

    fun startProgress() {
        viewModelScope.launch(Dispatchers.Main){
            delay(4000)
            flagFinishProgress.value = true
        }
    }

}