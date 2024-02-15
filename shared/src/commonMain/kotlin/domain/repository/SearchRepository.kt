package domain.repository

import domain.model.SearchRecentQuery
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getRecentSearchQueries(): Flow<List<SearchRecentQuery>>

    suspend fun searchByQuery(query: String)
}