package org.example.project.network

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object HttpClientProvider {
    fun create(): HttpClient {
        return HttpClient {
            // Configure timeouts to prevent timeout issues
            install(HttpTimeout) {
                requestTimeoutMillis = 30_000L // 30 seconds
                connectTimeoutMillis = 15_000L // 15 seconds
                socketTimeoutMillis = 30_000L  // 30 seconds
            }
            
            // Configure JSON serialization
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            
            // Configure logging for debugging
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
                filter { request ->
                    request.url.host.contains("api")
                }
            }
            
            // Configure default request settings
            install(DefaultRequest) {
                headers.append("User-Agent", "KMP-GameOpedia/1.0")
                headers.append("Accept", "application/json")
            }
            
            // Configure retry mechanism for failed requests
            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = 3)
                retryOnException(maxRetries = 3, retryOnTimeout = true)
                exponentialDelay()
            }
        }
    }
}