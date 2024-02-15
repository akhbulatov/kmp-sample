package core.network

import core.network.model.RepoNetModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class KmpSampleApi(
    private val httpClient: HttpClient
) {

    suspend fun getRepos(): List<RepoNetModel> {
        val httpResponse: HttpResponse = httpClient.get(urlString = "repositories")
        return httpResponse.body()
    }
}