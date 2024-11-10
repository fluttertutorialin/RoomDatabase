package org.multiplatform.room

import androidx.compose.ui.window.ComposeUIViewController

/*
fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }*/


fun MainViewController() = ComposeUIViewController {
    App()
}
