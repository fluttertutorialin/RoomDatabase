package org.multiplatform.room.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.multiplatform.room.feature.detail.presentation.NoteDetailScreen
import org.multiplatform.room.feature.notes.presentation.NotesScreen
import org.multiplatform.room.navigation.Destination.Detail
import org.multiplatform.room.navigation.Destination.Notes

@Composable
fun NotesAppGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Notes) {
        composable<Notes> {
            NotesScreen(
                navToDetail = { id ->
                    navController.navigate(Detail(id))
                }
            )
        }
        composable<Detail> {
            NoteDetailScreen(
                navBack = { navController.navigateUp() }
            )
        }
    }
}
