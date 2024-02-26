package data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.database.dao.SearchDao
import core.database.di.DatabaseFactory
import core.database.di.DatabaseSqlDriverFactory
import core.location.LocationClient
import core.location.di.LocationFactory
import core.network.di.NetworkFactory
import core.preferences.di.PreferencesFactory
import data.mapper.RepoNetworkMapper
import data.mapper.SearchDatabaseMapper
import data.repository.AuthRepositoryImpl
import data.repository.LocationRepositoryImpl
import data.repository.RepoRepositoryImpl
import data.repository.SearchRepositoryImpl
import domain.repository.AuthRepository
import domain.repository.LocationRepository
import domain.repository.RepoRepository
import domain.repository.SearchRepository

class DataFactory(
    private val sqlDriverFactory: DatabaseSqlDriverFactory,
    private val dataStore: DataStore<Preferences>,
    private val locationClient: LocationClient
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
    private val locationFactory by lazy {
        LocationFactory(
            locationClient = locationClient
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
    val locationRepository: LocationRepository by lazy {
        LocationRepositoryImpl(
            locationClient = locationFactory.locationClient
        )
    }
}