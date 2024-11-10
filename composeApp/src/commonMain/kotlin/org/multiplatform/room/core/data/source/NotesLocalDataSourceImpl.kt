package org.multiplatform.room.core.data.source

import org.multiplatform.room.core.data.database.NotesDao
import org.multiplatform.room.core.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import org.multiplatform.room.core.data.source.NotesLocalDataSource

class NotesLocalDataSourceImpl(
    private val notesDao: NotesDao
) : NotesLocalDataSource {

    override fun observeAllNotes(): Flow<List<NoteEntity>> {
        return notesDao.observeAllNotes()
    }

    override fun observeNoteById(id: Int): Flow<NoteEntity?> {
        return notesDao.observeNoteById(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        return notesDao.insertNote(note)
    }

    override suspend fun deleteNoteById(id: Int) {
        return notesDao.deleteNoteById(id)
    }
}
