package data.repository

import core.network.KmpSampleApi
import data.mapper.RepoNetworkMapper
import domain.model.Repo
import domain.repository.RepoRepository

class RepoRepositoryImpl(
    private val kmpSampleApi: KmpSampleApi,
    private val repoNetworkMapper: RepoNetworkMapper
) : RepoRepository {

    override suspend fun getRepos(): List<Repo> {
        return kmpSampleApi.getRepos()
            .let { repos ->
                repos.map { repo ->
                    repoNetworkMapper.mapRepoFromNet(repo)
                }
            }
    }
}