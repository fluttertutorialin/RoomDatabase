package org.multiplatform.room.core.data

import org.multiplatform.room.core.data.model.NoteEntity
import org.multiplatform.room.core.domain.Note

fun NoteEntity.toDomain(): Note {
    return Note(
        id = id,
        title = title,
        description = description
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description
    )
}