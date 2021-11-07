package com.example.stopwatch

class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculatorInterface,
    private val elapsedTimeCalculator: ElapsedTimeCalculatorInterface,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatterInterface,
): StopwatchStateHolderInterface {

    var currentState: StopwatchState = StopwatchState.Paused(0)
        private set

    override fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    override fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    override fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    override fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }
}