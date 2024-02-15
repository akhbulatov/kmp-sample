package core.database.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import orgexamplekmpsampleshareddatabase.SearchDatabaseQueries
import orgexamplekmpsampleshareddatabase.SearchRecentQueryDbModel

class SearchDao(private val searchDatabaseQueries: SearchDatabaseQueries) {

    fun getAllSearchRecentQueries(): Flow<List<SearchRecentQueryDbModel>> {
        return searchDatabaseQueries.getAllSearchRecentQueries()
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    suspend fun insertSearchQuery(query: String) {
        withContext(Dispatchers.IO) {
            searchDatabaseQueries.insertSearchRecentQuery(text = query)
        }
    }
}