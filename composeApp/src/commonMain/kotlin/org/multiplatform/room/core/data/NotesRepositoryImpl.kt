package org.multiplatform.room.core.data

import org.multiplatform.room.core.data.source.NotesLocalDataSource
import org.multiplatform.room.core.domain.NotesRepository
import org.multiplatform.room.core.domain.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl(
    private val notesLocalDataSource: NotesLocalDataSource
) : NotesRepository {

    override fun observeAllNotes(): Flow<List<Note>> {
        return notesLocalDataSource.observeAllNotes().map { notes ->
            notes.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun observeNoteById(id: Int): Flow<Note?> {
        return notesLocalDataSource.observeNoteById(id).map { entity ->
            entity?.toDomain()
        }
    }

    override suspend fun insertNote(note: Note) {
        return notesLocalDataSource.insertNote(note.toEntity())
    }

    override suspend fun deleteNoteById(id: Int) {
        notesLocalDataSource.deleteNoteById(id)
    }
}
