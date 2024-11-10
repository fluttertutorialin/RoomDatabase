package org.multiplatform.room.feature.detail.domain

import org.multiplatform.room.core.domain.NotesRepository
import org.multiplatform.room.core.domain.Note
import kotlinx.coroutines.flow.Flow

class ObserveNoteUseCase(
    private val repository: NotesRepository
) {
    operator fun invoke(id: Int): Flow<Note?> {
        return repository.observeNoteById(id)
    }
}
