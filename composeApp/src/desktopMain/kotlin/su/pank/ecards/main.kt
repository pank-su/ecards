package su.pank.ecards

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import su.pank.ecards.ui.game.CardSelector

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ecards",
    ) {
        App()
    }
}