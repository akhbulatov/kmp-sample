package di

import core.database.di.DatabaseSqlDriverFactory
import data.di.DataFactory
import domain.repository.RepoRepository
import domain.repository.SearchRepository

class CommonFactory(private val sqlDriverFactory: DatabaseSqlDriverFactory) {

    private val dataFactory by lazy {
        DataFactory(
            sqlDriverFactory = sqlDriverFactory
        )
    }

    val repoRepository: RepoRepository get() = dataFactory.repoRepository
    val searchRepository: SearchRepository get() = dataFactory.searchRepository
}