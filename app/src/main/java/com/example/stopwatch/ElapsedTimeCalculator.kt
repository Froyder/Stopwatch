package com.example.stopwatch

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProviderInterface,
): ElapsedTimeCalculatorInterface {

    override fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}