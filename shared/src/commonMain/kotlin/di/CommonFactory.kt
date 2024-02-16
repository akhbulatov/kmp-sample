package di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.database.di.DatabaseSqlDriverFactory
import data.di.DataFactory
import domain.repository.AuthRepository
import domain.repository.RepoRepository
import domain.repository.SearchRepository

class CommonFactory(
    private val sqlDriverFactory: DatabaseSqlDriverFactory,
    private val dataStore: DataStore<Preferences>
) {

    private val dataFactory by lazy {
        DataFactory(
            sqlDriverFactory = sqlDriverFactory,
            dataStore = dataStore
        )
    }

    val repoRepository: RepoRepository get() = dataFactory.repoRepository
    val searchRepository: SearchRepository get() = dataFactory.searchRepository
    val authRepository: AuthRepository get() = dataFactory.authRepository
}