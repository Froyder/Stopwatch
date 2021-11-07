package com.example.stopwatch

interface StopwatchStateHolderInterface {

    fun start()
    fun pause()
    fun stop()
    fun getStringTimeRepresentation(): String

}