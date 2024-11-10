package org.multiplatform.room.di

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.multiplatform.room.core.data.database.NotesDatabase

actual val platformModule = module {
    single<NotesDatabase> { createNotesDatabase(get()) }
}

private fun createNotesDatabase(context: Context): NotesDatabase {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("notes.db")

    return Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
