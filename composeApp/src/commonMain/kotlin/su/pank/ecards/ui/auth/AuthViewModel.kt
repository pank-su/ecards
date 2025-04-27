package su.pank.ecards.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import su.pank.ecards.data.AuthRepository
import su.pank.ecards.data.models.UserData
import kotlin.time.Duration.Companion.seconds


sealed interface AuthState{
    object Loading: AuthState
    object NeedLogin: AuthState
    data class Success(val user: UserInfo): AuthState
}

class AuthViewModel(private val userData: UserData?, private val authRepository: AuthRepository): ViewModel() {
    var isSend = false

    val state: SharedFlow<AuthState> = authRepository.sessionStatus.map { status ->
        when (status){
            is SessionStatus.Authenticated -> AuthState.Success(authRepository.getUser()!!)
            SessionStatus.Initializing -> AuthState.Loading
            is SessionStatus.NotAuthenticated -> {
                delay(3.seconds )
                if (isSend) delay(7.seconds)
                AuthState.NeedLogin
            }
            is SessionStatus.RefreshFailure -> {
                AuthState.Loading
            }
        }
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)

    init {
        viewModelScope.launch {
            userData?.let { authRepository.authByUserData(it)
            isSend = true}

        }
    }
}