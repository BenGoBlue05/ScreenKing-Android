package com.example.screenking.api

import java.util.concurrent.TimeUnit

interface RateLimiter<T> {

    fun reset(key: T)

    fun shouldFetch(key: T): Boolean
}

class DefaultRateLimiter<T>(timeOut: Int, timeUnit: TimeUnit) : RateLimiter<T> {

    private val timestamps: MutableMap<T, Long> = mutableMapOf()

    private val timeOut = timeUnit.toMillis(timeOut.toLong())

    override fun shouldFetch(key: T): Boolean {
        val now = System.currentTimeMillis()
        val lastFetched = timestamps[key]
        if (lastFetched == null || now - lastFetched > timeOut) {
            timestamps[key] = now
            return true
        }
        return false
    }

    override fun reset(key: T) {
        timestamps.remove(key)
    }
}