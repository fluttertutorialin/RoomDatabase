package org.multiplatform.room.feature.notes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.multiplatform.room.core.domain.Note
import org.multiplatform.room.core.presentation.components.NotesSwipeToDismissBox
import org.multiplatform.room.core.presentation.components.NotesTopAppBar
import org.multiplatform.room.core.presentation.theme.dimens
import org.multiplatform.room.feature.notes.presentation.NotesViewModel.NotesUiState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import roomdatabase.composeapp.generated.resources.Res
import roomdatabase.composeapp.generated.resources.common_content_description_add
import roomdatabase.composeapp.generated.resources.notes_screen_add_note_button_text
import roomdatabase.composeapp.generated.resources.notes_screen_empty_state
import roomdatabase.composeapp.generated.resources.notes_screen_title


@Composable
fun NotesScreen(
    viewModel: NotesViewModel = koinViewModel(),
    navToDetail: (Int?) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Content(
        state = state,
        onDetail = navToDetail,
        onDelete = viewModel::deleteNote
    )
}

@Composable
private fun Content(
    state: NotesUiState,
    onDetail: (Int?) -> Unit,
    onDelete: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            NotesTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.notes_screen_title)
                    )
                }
            )
        },
        floatingActionButton = {
            AddNoteFloatingActionButton(
                onClick = onDetail
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (state) {
                is NotesUiState.EMPTY -> EmptyNotesContent()
                is NotesUiState.NOTES -> {
                    NotesContent(
                        notes = state.notes,
                        onDetail = onDetail,
                        onDelete = onDelete
                    )
                }
            }
        }
    }
}

@Composable
private fun NotesContent(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onDetail: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(MaterialTheme.dimens.small),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.small),
    ) {
        items(notes, key = { item -> item.id }) { note ->
            NoteItem(
                note = note,
                onDetailClick = onDetail,
                onDeleteClick = onDelete
            )
        }
    }
}

@Composable
private fun NoteItem(
    note: Note,
    onDetailClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NotesSwipeToDismissBox(action = { onDeleteClick(note.id) }) {
        ElevatedCard(
            modifier = modifier,
            onClick = { onDetailClick(note.id) }
        ) {
            ListItem(
                headlineContent = {
                    Text(
                        text = note.title,
                        maxLines = 1,
                        overflow = Ellipsis
                    )
                },
                supportingContent = {
                    if (!note.description.isNullOrEmpty()) {
                        Text(
                            text = note.description,
                            maxLines = 2,
                            overflow = Ellipsis
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun AddNoteFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: (Int?) -> Unit
) {
    ExtendedFloatingActionButton(
        modifier = modifier,
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(Res.string.common_content_description_add)
            )
        },
        text = {
            Text(
                text = stringResource(Res.string.notes_screen_add_note_button_text)
            )
        },
        onClick = { onClick(null) }
    )
}

@Composable
private fun EmptyNotesContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(Res.string.notes_screen_empty_state))
    }
}
