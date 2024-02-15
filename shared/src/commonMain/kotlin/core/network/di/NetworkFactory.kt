package core.network.di

import core.network.KmpSampleApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
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
        }
    }

    val kmpSampleApi: KmpSampleApi by lazy {
        KmpSampleApi(
            httpClient = httpClient
        )
    }
}