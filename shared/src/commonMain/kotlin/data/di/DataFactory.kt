package data.di

import core.database.dao.SearchDao
import core.database.di.DatabaseFactory
import core.database.di.DatabaseSqlDriverFactory
import core.network.di.NetworkFactory
import data.mapper.RepoNetworkMapper
import data.mapper.SearchDatabaseMapper
import data.repository.RepoRepositoryImpl
import data.repository.SearchRepositoryImpl
import domain.repository.RepoRepository
import domain.repository.SearchRepository
import org.example.kmpsample.shared.database.Database

class DataFactory(private val sqlDriverFactory: DatabaseSqlDriverFactory) {

    private val networkFactory by lazy {
        NetworkFactory()
    }
    private val databaseFactory by lazy {
        DatabaseFactory(
            sqlDriverFactory = sqlDriverFactory
        )
    }

    val repoRepository: RepoRepository by lazy {
        RepoRepositoryImpl(
            kmpSampleApi = networkFactory.kmpSampleApi,
            repoNetworkMapper = RepoNetworkMapper()
        )
    }
    val searchRepository: SearchRepository by lazy {
        SearchRepositoryImpl(
            searchDao = SearchDao(
                searchDatabaseQueries = databaseFactory.searchDatabaseQueries
            ),
            searchDatabaseMapper = SearchDatabaseMapper()
        )
    }
}