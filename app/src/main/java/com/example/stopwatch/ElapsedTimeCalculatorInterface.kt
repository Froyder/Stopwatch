package com.example.stopwatch

interface ElapsedTimeCalculatorInterface {
    fun calculate(state: StopwatchState.Running): Long
}