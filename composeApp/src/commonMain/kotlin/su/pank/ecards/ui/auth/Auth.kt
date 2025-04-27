package su.pank.ecards.ui.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import io.ktor.http.parametersOf
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parameterSetOf
import su.pank.ecards.data.models.UserData

@Serializable
object Auth

@Composable
fun Auth(userData: UserData? = null, onAuthComplete: () -> Unit,){
    val vm = koinViewModel<AuthViewModel> { parameterSetOf(userData) }

    val state by vm.state.collectAsState(AuthState.Loading)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        when (state){
            AuthState.Loading -> CircularProgressIndicator()
            AuthState.NeedLogin -> {
                val uriHandler = LocalUriHandler.current
                LaunchedEffect(Unit){
                    //openUrl(uriHandler,"https://pank-su.github.io/telegram-login-test/")
                }
                Text("Перейдите по ссылке")
            }
            is AuthState.Success -> {
                LaunchedEffect(Unit){
                    onAuthComplete()
                }
            }
        }

    }
}

expect fun openUrl(uriHandler: UriHandler, url: String)