package org.multiplatform.room.feature.detail.domain

import org.multiplatform.room.core.domain.NotesRepository
import org.multiplatform.room.core.domain.Note

class SaveNoteUseCase(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(note: Note) {
        notesRepository.insertNote(note)
    }
}
