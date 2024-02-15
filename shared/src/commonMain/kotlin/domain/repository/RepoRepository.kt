package domain.repository

import domain.model.Repo

interface RepoRepository {
    suspend fun getRepos(): List<Repo>
}