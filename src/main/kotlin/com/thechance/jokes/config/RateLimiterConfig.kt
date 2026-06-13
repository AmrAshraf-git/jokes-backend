package com.thechance.jokes.config

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap

@Component
class RateLimiterConfig {
    private val buckets = ConcurrentHashMap<String, Bucket>()

    fun resolveBucket(ipAddress: String): Bucket {
        println("Map size: ${buckets.size}")
        println("IP: $ipAddress")
        return buckets.getOrPut(ipAddress) { createNewBucket() }
    }

    private fun createNewBucket(): Bucket {
        val limit = Bandwidth.builder()
            .capacity(10)
            .refillGreedy(10, Duration.ofMinutes(1))
            .build()

        return Bucket.builder()
            .addLimit(limit)
            .build()
    }
}