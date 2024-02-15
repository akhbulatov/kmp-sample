package data.mapper

import domain.model.SearchRecentQuery
import orgexamplekmpsampleshareddatabase.SearchRecentQueryDbModel

class SearchDatabaseMapper {

    fun mapFromDb(dbModel: SearchRecentQueryDbModel): SearchRecentQuery {
        return SearchRecentQuery(text = dbModel.text!!) // TODO
    }
}