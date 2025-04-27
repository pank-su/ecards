package su.pank.ecards.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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
                Column {
                    Text("Ищем игру", color = Color.White)
                    CircularProgressIndicator()
                }
            }

            is StartState.Menu -> {
                val user = (state as StartState.Menu).userInfo
                LaunchedEffect(null){
                    println(user.userMetadata?.getValue("avatar_url").toString().replace("\"", ""))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.align(Alignment.Center)) {

                    AsyncImage(
                        model = user.userMetadata?.getValue("avatar_url")?.toString()?.replace("\"", ""),
                        null,
                        modifier = Modifier.size(60.dp),
                        contentScale = ContentScale.Crop,
                        onError = {
                            println(it)
                        }
                    )
                    Button(onClick = {
                    }) {
                        Text("Найти игру")
                    }
                }
            }

            StartState.Loading -> {
                CircularProgressIndicator()
            }
        }
        // OutlinedTextField()
    }
}