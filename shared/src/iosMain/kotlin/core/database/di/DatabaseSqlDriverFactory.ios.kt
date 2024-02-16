package core.database.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.kmpsample.shared.database.Database

actual class DatabaseSqlDriverFactory {

    actual val sqlDriver: SqlDriver
        get() {
            return NativeSqliteDriver(
                schema = Database.Schema,
                name = DATABASE_NAME
            )
        }
}