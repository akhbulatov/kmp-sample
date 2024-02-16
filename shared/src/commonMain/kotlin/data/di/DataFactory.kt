package data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.database.dao.SearchDao
import core.database.di.DatabaseFactory
import core.database.di.DatabaseSqlDriverFactory
import core.network.di.NetworkFactory
import core.preferences.di.PreferencesFactory
import data.mapper.RepoNetworkMapper
import data.mapper.SearchDatabaseMapper
import data.repository.AuthRepositoryImpl
import data.repository.RepoRepositoryImpl
import data.repository.SearchRepositoryImpl
import domain.repository.AuthRepository
import domain.repository.RepoRepository
import domain.repository.SearchRepository

class DataFactory(
    private val sqlDriverFactory: DatabaseSqlDriverFactory,
    private val dataStore: DataStore<Preferences>
) {

    private val networkFactory by lazy {
        NetworkFactory()
    }
    private val databaseFactory by lazy {
        DatabaseFactory(
            sqlDriverFactory = sqlDriverFactory
        )
    }
    private val preferencesFactory by lazy {
        PreferencesFactory(
            dataStore = dataStore
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
    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(
            appPreferences = preferencesFactory.appPreferences
        )
    }
}