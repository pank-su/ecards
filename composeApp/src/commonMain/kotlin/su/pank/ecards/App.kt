package su.pank.ecards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import ecards.composeapp.generated.resources.Res
import ecards.composeapp.generated.resources.compose_multiplatform
import su.pank.ecards.ui.game.Game
import su.pank.ecards.ui.start.StartScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        Game()
    }
}