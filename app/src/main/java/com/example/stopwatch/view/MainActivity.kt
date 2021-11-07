package com.example.stopwatch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch.viewmodel.MainViewModel
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        viewModel.mutableLiveData.observe(this) { viewBinding.textTime.text = it }

        viewBinding.buttonStart.setOnClickListener {
            viewModel.start()
        }
        viewBinding.buttonPause.setOnClickListener {
            viewModel.pause()
        }
        viewBinding.buttonStop.setOnClickListener {
            viewModel.stop()
        }
    }
}

