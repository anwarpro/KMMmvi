package com.helloanwar.kmmmvi.data_cache.sqldelight

import android.content.Context
import com.helloanwar.kmmmvi.shared.data_cache.sqldelight.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "test.db")
    }
}