package su.pank.ecards.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import ecards.composeapp.generated.resources.Res
import ecards.composeapp.generated.resources.menubg
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object Start

@Composable
fun StartScreen() {

    val vm = koinViewModel<StartViewModel>()
    val state by vm.state.collectAsState()

    Box(Modifier.fillMaxSize()) {
        Image(
            painterResource(Res.drawable.menubg),
            null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        when (state) {
            is StartState.Find -> {
                CircularProgressIndicator()
            }

            is StartState.NameEdited -> {
                val name = (state as StartState.NameEdited).name
                Row(modifier = Modifier.align(Alignment.Center)) {
                    TextField(name, { vm.onNameSet(name) })
                    FloatingActionButton(onClick = {}, ){
                    }
                }
            }
        }
        // OutlinedTextField()
    }
}