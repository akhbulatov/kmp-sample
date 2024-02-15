package data.repository

import core.database.dao.SearchDao
import data.mapper.SearchDatabaseMapper
import domain.model.SearchRecentQuery
import domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRepositoryImpl(
    private val searchDao: SearchDao,
    private val searchDatabaseMapper: SearchDatabaseMapper
) : SearchRepository {

    override fun getRecentSearchQueries(): Flow<List<SearchRecentQuery>> {
        return searchDao.getAllSearchRecentQueries()
            .map { dbModelList ->
                dbModelList.map { dbModel ->
                    searchDatabaseMapper.mapFromDb(dbModel)
                }
            }
    }

    override suspend fun searchByQuery(query: String) {
        searchDao.insertSearchQuery(query)
    }
}