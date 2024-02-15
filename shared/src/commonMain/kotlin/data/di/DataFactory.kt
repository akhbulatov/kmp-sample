package data.di

import core.network.di.NetworkFactory
import data.mapper.RepoNetworkMapper
import data.repository.RepoRepositoryImpl
import domain.repository.RepoRepository

class DataFactory {

    private val networkFactory by lazy {
        NetworkFactory()
    }

    val repoRepository: RepoRepository by lazy {
        RepoRepositoryImpl(
            kmpSampleApi = networkFactory.kmpSampleApi,
            repoNetworkMapper = RepoNetworkMapper()
        )
    }
}