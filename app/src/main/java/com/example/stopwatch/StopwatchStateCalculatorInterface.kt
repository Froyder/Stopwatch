package com.example.stopwatch

interface StopwatchStateCalculatorInterface {

    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running

    fun calculatePausedState(oldState: StopwatchState): StopwatchState.Paused

}