package com.example.weatherforyourcity.view.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforyourcity.databinding.FragmentFirstBinding
import com.example.weatherforyourcity.viewmodel.FirstFragmentViewModel


class FirstFragment : Fragment(){

    private var binding: FragmentFirstBinding? = null
    private var firstViewModel:FirstFragmentViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding?.root
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstViewModel = ViewModelProvider(this)[FirstFragmentViewModel::class.java]

        //асинхронная отправка запроса о получении погоды для трех городов
        firstViewModel?.getPopularWeatherInCity()
        firstViewModel?.weatherInCity?.observe(viewLifecycleOwner){ response ->
            when(firstViewModel?.numberCity){
                1 -> {
                    binding!!.idFirstCs1Temp.text = "${response.body()!!.main.temp.toInt()-273}°C"
                    binding!!.idFirstCs1TvCity.text = response.body()!!.name
                    binding!!.idFirstCs1TvDesc.text = response.body()!!.weather[0].main
                }
                2 -> {
                    binding!!.idFirstCs2Temp.text = "${response.body()!!.main.temp.toInt()-273}°C"
                    binding!!.idFirstCs2TvCity.text = response.body()!!.name
                    binding!!.idFirstCs2TvDesc.text = response.body()!!.weather[0].main
                }
                3 -> {
                    binding!!.idFirstCs3Temp.text = "${response.body()!!.main.temp.toInt()-273}°C"
                    binding!!.idFirstCs3TvCity.text = response.body()!!.name
                    binding!!.idFirstCs3TvDesc.text = response.body()!!.weather[0].main
                }
            }
        }

        //переход ко второму экрану для показа дополнительной информации по 1 городу
        binding!!.idFirstCs1.setOnClickListener {
            firstViewModel?.goToSecondFragmentForShowInformation(binding!!.idFirstCs1TvCity.text.toString())
        }

        //переход ко второму экрану для показа дополнительной информации по 2 городу
        binding!!.idFirstCs2.setOnClickListener {
            firstViewModel?.goToSecondFragmentForShowInformation(binding!!.idFirstCs2TvCity.text.toString())
        }

        //переход ко второму экрану для показа дополнительной информации по 3 городу
        binding!!.idFirstCs3.setOnClickListener {
            firstViewModel?.goToSecondFragmentForShowInformation(binding!!.idFirstCs3TvCity.text.toString())
        }

        //переход к поиску погоды
        binding!!.idFirstCsFind.setOnClickListener {
            firstViewModel?.goToSecondFragment()
        }

        //выход из игры
        binding!!.idFirstButtonExit.setOnClickListener {
            showExitDialog()
        }

        //выход из игры
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            showExitDialog()
        }

    }

    //очистка биндинга при очистке вью
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //функция показа диалогового сообщения
    private fun showExitDialog() {
        val options = arrayOf("exit", "cancel")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("do you want to get out?")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    firstViewModel?.exitingApplication()
                }
                1 -> {
                    dialog.cancel()
                }
            }
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}