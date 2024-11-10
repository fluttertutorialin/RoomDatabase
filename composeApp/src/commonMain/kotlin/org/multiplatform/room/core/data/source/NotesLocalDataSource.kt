package org.multiplatform.room.core.data.source

import org.multiplatform.room.core.data.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {

    fun observeAllNotes(): Flow<List<NoteEntity>>

    fun observeNoteById(id: Int): Flow<NoteEntity?>

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNoteById(id: Int)
}
