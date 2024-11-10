package org.multiplatform.room.di

import org.multiplatform.room.core.data.NotesRepositoryImpl
import org.multiplatform.room.core.data.database.NotesDatabase
import org.multiplatform.room.core.data.source.NotesLocalDataSource
import org.multiplatform.room.core.data.source.NotesLocalDataSourceImpl
import org.multiplatform.room.core.domain.NotesRepository
import org.multiplatform.room.feature.notes.domain.DeleteNoteUseCase
import org.multiplatform.room.feature.detail.domain.ObserveNoteUseCase
import org.multiplatform.room.feature.notes.domain.ObserveNotesUseCase
import org.multiplatform.room.feature.detail.domain.SaveNoteUseCase
import org.multiplatform.room.feature.detail.presentation.NoteDetailViewModel
import org.multiplatform.room.feature.notes.presentation.NotesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val commonModule = module {
    single { get<NotesDatabase>().notesDao() }
    single<NotesLocalDataSource> { NotesLocalDataSourceImpl(get()) }
    single<NotesRepository> { NotesRepositoryImpl(get()) }

    factory { ObserveNotesUseCase(get()) }
    factory { ObserveNoteUseCase(get()) }
    factory { SaveNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }

    viewModelOf(::NotesViewModel)
    viewModelOf(::NoteDetailViewModel)
}
