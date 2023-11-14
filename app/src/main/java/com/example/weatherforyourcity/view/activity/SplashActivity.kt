package com.example.weatherforyourcity.view.activity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforyourcity.databinding.ActivitySplashBinding
import com.example.weatherforyourcity.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //старт анимации загрузки
        startAnimationProgress()

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        splashViewModel.startProgress()

        //переход к новому экрану
        splashViewModel.flagFinishProgress.observe(this){
            goToMainActivity()
        }
        
    }

    //функция показа анимации загрузки
    private fun startAnimationProgress(){
        binding.idSplashPb.max = 2000
        val finishProgress = 2000
        ObjectAnimator.ofInt(binding.idSplashPb,"progress",finishProgress)
            .setDuration(4000)
            .start()
    }

    //переход к основной части приложения
    private fun goToMainActivity(){
        startActivity(Intent(this,MainActivity::class.java))
    }

}