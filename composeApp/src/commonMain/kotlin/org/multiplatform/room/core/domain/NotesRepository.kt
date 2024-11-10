package org.multiplatform.room.core.domain

import kotlinx.coroutines.flow.Flow
import org.multiplatform.room.core.domain.Note

interface NotesRepository {

    fun observeAllNotes(): Flow<List<Note>>

    fun observeNoteById(id: Int): Flow<Note?>

    suspend fun insertNote(note: Note)

    suspend fun deleteNoteById(id: Int)
}
