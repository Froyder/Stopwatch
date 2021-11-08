package com.example.stopwatch.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch.viewmodel.MainViewModel
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.viewmodel.SecondViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private val firstViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val secondViewModel: SecondViewModel by lazy {
        ViewModelProvider(this).get(SecondViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        firstViewModel.firstLiveData.observe(this) { viewBinding.textTime.text = it }
        secondViewModel.secondLiveData.observe(this) { viewBinding.textTime2.text = it }

        viewBinding.buttonStart.setOnClickListener {
            firstViewModel.start()
        }
        viewBinding.buttonPause.setOnClickListener {
            firstViewModel.pause()
        }
        viewBinding.buttonStop.setOnClickListener {
            firstViewModel.stop()
        }

        viewBinding.buttonStart2.setOnClickListener {
            secondViewModel.start()
        }
        viewBinding.buttonPause2.setOnClickListener {
            secondViewModel.pause()
        }
        viewBinding.buttonStop2.setOnClickListener {
            secondViewModel.stop()
        }

        viewBinding.buttonSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

    }
}

