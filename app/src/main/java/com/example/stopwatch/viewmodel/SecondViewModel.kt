package com.example.stopwatch.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stopwatch.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class SecondViewModel: ViewModel() {

    private val _secondLiveData = MutableLiveData<String>()
    val secondLiveData: LiveData<String>
        get() = _secondLiveData

    private val viewModelScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private val timestampProvider = TimestampProvider()

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    private var job: Job? = null

    fun start() {
        stopwatchListOrchestrator.start()
        job?.cancel()
        job = viewModelScope.launch {
            stopwatchListOrchestrator.ticker.collect { _secondLiveData.postValue(it) }
        }
    }

    fun stop() {
        stopwatchListOrchestrator.stop()
    }

    fun pause() {
        stopwatchListOrchestrator.pause()
    }

    private fun handleError(error: Throwable) {
        Log.e("Error_tag","An error occurred: $error")
    }

    override fun onCleared() {
        _secondLiveData.value = null
        viewModelScope.cancel()
        super.onCleared()
    }

}