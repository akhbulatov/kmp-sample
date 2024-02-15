package core.database.di

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.kmpsample.shared.database.Database

actual class DatabaseSqlDriverFactory(private val context: Context) {

    actual val sqlDriver: SqlDriver
        get() {
            return AndroidSqliteDriver(
                schema = Database.Schema,
                context = context,
                name = DATABASE_NAME
            )
        }
}