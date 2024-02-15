package core.network.di

import core.network.KmpSampleApi
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkFactory {

    private val httpClient: HttpClient by lazy {
        HttpClient {
            defaultRequest {
                url(urlString = "https://api.github.com/")
            }

            install(plugin = ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }
            install(plugin = Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.d(tag = "HttpClient", message = message)
                    }
                }
                level = LogLevel.BODY
            }
        }
    }

    val kmpSampleApi: KmpSampleApi by lazy {
        KmpSampleApi(
            httpClient = httpClient
        )
    }
}