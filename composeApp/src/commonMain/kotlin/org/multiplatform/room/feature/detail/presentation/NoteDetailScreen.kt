package org.multiplatform.room.feature.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.multiplatform.room.core.presentation.components.NotesIconButton
import org.multiplatform.room.core.presentation.components.NotesTextField
import org.multiplatform.room.core.presentation.components.NotesTopAppBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import roomdatabase.composeapp.generated.resources.Res
import roomdatabase.composeapp.generated.resources.common_content_description_back
import roomdatabase.composeapp.generated.resources.common_content_description_save
import roomdatabase.composeapp.generated.resources.note_detail_screen_note_description_placeholder
import roomdatabase.composeapp.generated.resources.note_detail_screen_note_title_placeholder

@Composable
fun NoteDetailScreen(
    viewModel: NoteDetailViewModel = koinViewModel(),
    navBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Content(
        state = state,
        onBack = navBack,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onSave = viewModel::saveNote
    )
}

@Composable
private fun Content(
    state: NoteDetailViewModel.NoteDetailUiState,
    onBack: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NotesTopAppBar(
                title = {},
                navigationIcon = {
                    NotesIconButton(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(
                            Res.string.common_content_description_back
                        ),
                        onClick = onBack
                    )
                },
                actions = {
                    NotesIconButton(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(
                            Res.string.common_content_description_save
                        ),
                        onClick = {
                            onSave()
                            onBack()
                        }
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            NotesTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.title,
                singleLine = true,
                textStyle = MaterialTheme.typography.titleLarge,
                onValueChange = onTitleChange,
                placeholder = stringResource(Res.string.note_detail_screen_note_title_placeholder)
            )
            NotesTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.description.orEmpty(),
                onValueChange = onDescriptionChange,
                placeholder = stringResource(
                    Res.string.note_detail_screen_note_description_placeholder
                )
            )
        }
    }
}