package com.example.weatherforyourcity.view.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.weatherforyourcity.R
import com.example.weatherforyourcity.databinding.ActivityMainBinding
import com.example.weatherforyourcity.model.constant.MAIN


class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    var navController : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        MAIN = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

    }
}