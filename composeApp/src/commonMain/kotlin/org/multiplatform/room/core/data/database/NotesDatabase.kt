package org.multiplatform.room.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.multiplatform.room.core.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@ConstructedBy(NotesDatabaseConstructor::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
internal expect object NotesDatabaseConstructor : RoomDatabaseConstructor<NotesDatabase>
