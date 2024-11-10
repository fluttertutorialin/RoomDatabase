package org.multiplatform.room

import androidx.compose.runtime.Composable
import org.multiplatform.room.core.presentation.theme.NotesTheme
import org.multiplatform.room.navigation.NotesAppGraph
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        NotesTheme {
            NotesAppGraph()
        }
    }
}
