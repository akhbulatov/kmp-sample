package core.database.di

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseSqlDriverFactory {
    val sqlDriver: SqlDriver
}

const val DATABASE_NAME = "kmpsample.db"