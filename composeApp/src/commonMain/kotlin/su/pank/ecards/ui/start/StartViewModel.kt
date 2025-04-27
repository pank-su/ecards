package su.pank.ecards.ui.start


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed interface StartState{
    data object Find: StartState
    data class Menu(val userInfo: UserInfo): StartState

    object  Loading: StartState
}

class StartViewModel(private val client: SupabaseClient): ViewModel() {

    private val _state = MutableStateFlow<StartState>(StartState.Loading)
    val state = _state.asStateFlow()


    init {
        viewModelScope.launch {
            println("HI")
            val user =   client.auth.currentUserOrNull()

            user?.let { _state.value = StartState.Menu(it) }
        }
    }

}