package di

import data.di.DataFactory
import domain.repository.RepoRepository

object CommonFactory {

    private val dataFactory by lazy {
        DataFactory()
    }

    val repoRepository: RepoRepository get() = dataFactory.repoRepository
}