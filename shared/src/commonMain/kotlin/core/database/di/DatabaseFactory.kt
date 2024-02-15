package core.database.di

import org.example.kmpsample.shared.database.Database
import orgexamplekmpsampleshareddatabase.SearchDatabaseQueries

class DatabaseFactory(private val sqlDriverFactory: DatabaseSqlDriverFactory) {

    private val database: Database by lazy {
        Database(sqlDriverFactory.sqlDriver)
    }

    val searchDatabaseQueries: SearchDatabaseQueries by lazy {
        database.searchDatabaseQueries
    }
}