package org.multiplatform.room.feature.notes.domain

import org.multiplatform.room.core.domain.NotesRepository

class DeleteNoteUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(id: Int) {
        notesRepository.deleteNoteById(id)
    }
}
