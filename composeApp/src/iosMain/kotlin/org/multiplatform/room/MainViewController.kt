package org.multiplatform.room

import androidx.compose.ui.window.ComposeUIViewController
import org.multiplatform.room.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}
