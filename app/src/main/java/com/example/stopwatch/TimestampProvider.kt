package com.example.stopwatch

class TimestampProvider : TimestampProviderInterface {
    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}