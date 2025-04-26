package su.pank.ecards.ui.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import ecards.composeapp.generated.resources.Res
import ecards.composeapp.generated.resources.menubg
import org.jetbrains.compose.resources.painterResource

@Composable
fun StartScreen() {



    Box(Modifier.fillMaxSize()) {
        Image(painterResource(Res.drawable.menubg), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())

        // OutlinedTextField()
    }
}