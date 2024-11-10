package org.multiplatform.room.feature.notes.domain

import org.multiplatform.room.core.domain.NotesRepository
import org.multiplatform.room.core.domain.Note
import kotlinx.coroutines.flow.Flow

class ObserveNotesUseCase(
    private val notesRepository: NotesRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return notesRepository.observeAllNotes()
    }
}
