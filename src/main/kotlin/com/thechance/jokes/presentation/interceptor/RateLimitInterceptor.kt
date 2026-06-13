package com.thechance.jokes.presentation.interceptor

import com.thechance.jokes.config.RateLimiterConfig
import com.thechance.jokes.domain.exception.RateLimitException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class RateLimitInterceptor(
    private val rateLimiterConfig: RateLimiterConfig
) : HandlerInterceptor {

    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {

        val ipAddress = getClientIp(request)
        val bucket = rateLimiterConfig.resolveBucket(ipAddress)
        val probe = bucket.tryConsumeAndReturnRemaining(1)

//        println("IP: $ipAddress")
//        println("Consumed: ${probe.isConsumed}")
//        println("Remaining: ${probe.remainingTokens}")

        if (probe.isConsumed) {
            response.addHeader(
                "X-Rate-Limit-Remaining",
                probe.remainingTokens.toString()
            )
            return true
        }

        val waitTime = probe.nanosToWaitForRefill / 1_000_000_000
        throw RateLimitException(
            "Too many requests! Try again in $waitTime seconds"
        )
    }
    private fun getClientIp(request: HttpServletRequest): String {
        return request.getHeader("X-Forwarded-For")?.split(",")?.first()?.trim()
            ?: request.getHeader("X-Real-IP")
            ?: request.remoteAddr
    }
}