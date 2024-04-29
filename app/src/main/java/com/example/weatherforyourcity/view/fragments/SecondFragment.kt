package com.example.weatherforyourcity.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforyourcity.databinding.FragmentSecondBinding
import com.example.weatherforyourcity.model.constant.MY_KEY
import com.example.weatherforyourcity.model.constant.WEATHER
import com.example.weatherforyourcity.viewmodel.SecondFragmentViewModel


class SecondFragment : Fragment() {

    private var binding : FragmentSecondBinding? = null
    private var secondViewModel : SecondFragmentViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding?.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondViewModel = ViewModelProvider(this)[SecondFragmentViewModel::class.java]

        //показ полученных данных
        secondViewModel?.weatherInCity?.observe(viewLifecycleOwner){
            binding?.idSecondCsInfo?.isVisible = true
            binding?.idSecondTvCity?.text = "${it?.body()?.name},${it?.body()?.sys?.country}" //город и страна
            binding?.idSecondTvTemp?.text = "${it?.body()?.main?.temp?.toInt()?.minus(273)}°C" // средняя температура
            binding?.idSecondTvMaxTemp?.text = "MAX:${it?.body()?.main?.temp_max?.toInt()?.minus(273)}°C" // максимальная температура
            binding?.idSecondTvMinTemp?.text = "MIN:${it?.body()?.main?.temp_min?.toInt()?.minus(273)}°C" // минимальная температура
            binding?.idSecondTvFeelsLike?.text = "FEELS LIKE:${it?.body()?.main?.feels_like?.toInt()?.minus(273)}°C" // ощущаемая температура
            binding?.idSecondTvMain?.text = it?.body()?.weather?.get(0)?.main // описание
            binding?.idSecondTvDesc?.text = it?.body()?.weather?.get(0)?.description // описание
            binding?.idSecondTvPressure?.text = "Atmospheric pressure on the sea level ${it?.body()?.main?.pressure}hPa" // давление
            binding?.idSecondTvHumidity?.text = "Humidity ${it?.body()?.main?.humidity}%" // влажность
            binding?.idSecondTvCloudsAll?.text = "Cloudiness ${it?.body()?.clouds?.all}%" // облачность
            binding?.idSecondTvVisibility?.text = "Visibility ${it?.body()?.visibility}m" // видимость
            binding?.idSecondTvWindSpeed?.text = "Wind speed ${it?.body()?.wind?.speed} meter/sec"
        }

        //показ данных
        arguments?.let{
            val nameCity = it.getString(WEATHER)
            secondViewModel?.getWeatherInCity(requireContext(),nameCity!!,"5add6a6a1179b651037584ea12d07431")
        }

        //поиск погоды
        binding?.idSecondButtonSearch?.setOnClickListener {
            if(binding?.idSecondEt?.text?.isNotEmpty() == true){
                secondViewModel?.getWeatherInCity(requireContext(),binding?.idSecondEt?.text.toString(),MY_KEY)
                binding?.idSecondEt?.text?.clear()
            }else{
                secondViewModel?.showToast(requireContext(),"you did not enter the city name")
            }
        }

        //показ первого экрана
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            secondViewModel?.goToFirstFragment()
        }

        //показ первого фрагмента
        binding?.idSecondButtonBack?.setOnClickListener {
            secondViewModel?.goToFirstFragment()
        }

    }

    //очистка биндинга при очистке вью
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}